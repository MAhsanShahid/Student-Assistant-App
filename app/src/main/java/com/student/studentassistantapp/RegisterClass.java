package com.student.studentassistantapp;

import android.app.Activity;
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
 * Created by TALHA on 17.4.16.
 */
public class  RegisterClass extends Activity
{

    Spinner crs;
    List <CourseClass> classes; // data from course class

    List<String> list1; // contain class names
    List<String> list2; // will contain section names

    List<String> final_data; // list1+ list2
    String user1=null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_class);
        user1 = getIntent().getStringExtra("email").toString();

        crs = (Spinner) findViewById(R.id.spinner1);

        list1 = new ArrayList<String>();
        final_data = new ArrayList<String>();
        list2= new ArrayList<String>();




        BackendlessDataQuery query1 = new BackendlessDataQuery();
        CourseClass.findAsync(query1, new AsyncCallback<BackendlessCollection<CourseClass>>() {
            @Override
            public void handleResponse(BackendlessCollection<CourseClass> courseBackendlessCollection) {
                classes= courseBackendlessCollection.getCurrentPage();


                for (int i = 0; i < classes.size(); i++) {
                    list1.add(classes.get(i).getCourse_name());
                    list2.add(classes.get(i).getSection_name());
                    final_data.add(list1.get(i).concat(" "+ list2.get(i)));

                }


                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_items, final_data);
                //adapter.setDropDownViewResource(R.layout.spinner_dropdown);
                crs.setAdapter(adapter);

            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Toast.makeText(getBaseContext(), backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }// on create

    public void register(View v) {

        String id=null;
        String select= crs.getSelectedItem().toString();
        //Toast.makeText(getBaseContext(),select, Toast.LENGTH_SHORT).show();

        Enrolment obj= new Enrolment();


        //int index=select.indexOf("Section");
        //Toast.makeText(getBaseContext(),index, Toast.LENGTH_SHORT).show();
        // String course= select.substring(0, index);
        // String section= select.substring(index);

/*
        for (int loop=0; loop< classes.size();loop++)

            {
                if (course.equals(classes.get(loop).getCourse_name()))
                    {
                        if (section.equals(classes.get(loop).getSection_name()))
                        {id= classes.get(loop).getCourse_id();}

                    }

            }

*/
        obj.setStudent_id(user1);
        obj.setClass_name(select);
        obj.setCreditHr_earned(0.0);
        obj.setGrade(null);
        obj.setClass_id(id);



        ///start here
        obj.saveAsync(new AsyncCallback<Enrolment>() {


            @Override
            public void handleResponse(Enrolment enrolment) {
                Toast.makeText(getBaseContext() , "Class Registered Successfully" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Toast.makeText(getBaseContext(), backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }


}