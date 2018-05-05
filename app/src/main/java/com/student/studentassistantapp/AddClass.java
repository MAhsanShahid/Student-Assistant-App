package com.student.studentassistantapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TALHA on 10.4.16.
 */
public class AddClass extends Activity {

    Spinner crs, sec;
    List<Course> cr;
    List<Section> secs;
    List<String> list;
    List<String> secList;
    static int cn=1;
    String user1;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_class);

        user1 = getIntent().getStringExtra("Teach").toString();
        crs = (Spinner) findViewById(R.id.spinner);
        sec = (Spinner) findViewById(R.id.spinner2);



        BackendlessDataQuery query = new BackendlessDataQuery();
        Course.findAsync(query, new AsyncCallback<BackendlessCollection<Course>>() {
            @Override
            public void handleResponse(BackendlessCollection<Course> sectionBackendlessCollection) {
                cr = sectionBackendlessCollection.getCurrentPage();

                list = new ArrayList<String>();

                for (int i = 0; i < cr.size(); i++) {
                    list.add(cr.get(i).getCourseName());
                }

                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_items, list);
                //adapter.setDropDownViewResource(R.layout.spinner_dropdown);
                crs.setAdapter(adapter1);
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Toast.makeText(getBaseContext(), backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        BackendlessDataQuery query1 = new BackendlessDataQuery();
        Section.findAsync(query1, new AsyncCallback<BackendlessCollection<Section>>() {
            @Override
            public void handleResponse(BackendlessCollection<Section> sectionBackendlessCollection) {
                secs = sectionBackendlessCollection.getCurrentPage();

                secList = new ArrayList<String>();

                for (int i = 0; i < secs.size(); i++) {
                    secList.add(secs.get(i).getSectionName());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_items, secList);
                //adapter.setDropDownViewResource(R.layout.spinner_dropdown);
                sec.setAdapter(adapter);
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Toast.makeText(getBaseContext(), backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void crDone(View v) {
        String crd = crs.getSelectedItem().toString();
        String scs = sec.getSelectedItem().toString();
        //cn++;
        String courseCode = null;
        Integer secid = 0;

        for (int i = 0; i < cr.size(); i++) {
            if (crd.equals(cr.get(i).getCourseName())) {
                courseCode = cr.get(i).getCourseID();
                break;
            }
        }

        for (int i = 0; i < secs.size(); i++) {
            if (scs.equals(secs.get(i).getSectionName())) {
                secid = secs.get(i).getSectionID();
                break;
            }
        }


       // Toast.makeText(getBaseContext(), secid, Toast.LENGTH_SHORT).show();

        Intent in = getIntent();
        CourseClass cc = new CourseClass();
        cc.setCourse_id(courseCode);
        cc.setCourse_name(crd);
        cc.setSection_id(secid);
        cc.setSection_name(scs);
        cc.setTeacher_id(user1);

        ///start here
        cc.saveAsync(new AsyncCallback<CourseClass>() {
            @Override
            public void handleResponse(CourseClass courseClass) {
                //cc = courseClass;
                //courseClass = cc;
                Toast.makeText(getBaseContext(), "Class Added Successfully!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Toast.makeText(getBaseContext(), backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}