package com.student.studentassistantapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.messaging.DeliveryOptions;
import com.backendless.messaging.MessageStatus;
import com.backendless.messaging.PublishOptions;
import com.backendless.persistence.BackendlessDataQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TALHA on 5.5.16.
 */
public class Swapper extends Activity {

    Spinner rc; // required course
    Spinner rs; // required section
    Spinner cc; // curent course
    Spinner cs; // current section
    List<Course> course_data;
    List<Section> section_data;
    List<Swap> swap;
    List<String> course;
    List<String> section;
    List<String> swap_data;
    String user2;
    String user1;
    String user1ID;
    String user2ID;
    boolean flag;
    TextView sts;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swapper);


        flag = false;
        sts = (TextView)findViewById(R.id.status);
        course = new ArrayList<String>();
        section = new ArrayList<String>();
        swap_data = new ArrayList<String>();
        /// get spinners id
        cc = (Spinner) findViewById(R.id.current_course);
        cs = (Spinner) findViewById(R.id.current_section);
        rc = (Spinner) findViewById(R.id.required_course);
        rs = (Spinner) findViewById(R.id.required_section);


        /// backendless query to fetch course data

        BackendlessDataQuery query4 = new BackendlessDataQuery();
        Course.findAsync(query4, new AsyncCallback<BackendlessCollection<Course>>() {
            @Override
            public void handleResponse(BackendlessCollection<Course> courseBackendlessCollection) {
                course_data = courseBackendlessCollection.getCurrentPage();


                for (int loop = 0; loop < course_data.size(); loop++) {

                    course.add(course_data.get(loop).getCourseName());

                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_items, course);
                //adapter.setDropDownViewResource(R.layout.spinner_dropdown);
                cc.setAdapter(adapter);
                rc.setAdapter(adapter);
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Toast.makeText(getBaseContext(), backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });

        ///// backendless query to fetch section data
        BackendlessDataQuery query5 = new BackendlessDataQuery();
        Section.findAsync(query5, new AsyncCallback<BackendlessCollection<Section>>() {
            @Override
            public void handleResponse(BackendlessCollection<Section> sectionBackendlessCollection) {
                section_data = sectionBackendlessCollection.getCurrentPage();


                for (int loop = 0; loop < section_data.size(); loop++) {

                    section.add(section_data.get(loop).getSectionName());

                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_items, section);
                //adapter.setDropDownViewResource(R.layout.spinner_dropdown);
                cs.setAdapter(adapter);
                rs.setAdapter(adapter);
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Toast.makeText(getBaseContext(), backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });

    }// on create


    public void Check(View v) {


        final String c_course = cc.getSelectedItem().toString();
        final String c_section = cs.getSelectedItem().toString();
        final String r_course = rc.getSelectedItem().toString();
        final String r_section = rs.getSelectedItem().toString();


        ///// backendless query to fetch swap data
        BackendlessDataQuery query6 = new BackendlessDataQuery();
        Swap.findAsync(query6, new AsyncCallback<BackendlessCollection<Swap>>() {
            @Override
            public void handleResponse(BackendlessCollection<Swap> swapBackendlessCollection) {
                swap = swapBackendlessCollection.getCurrentPage();
                // Toast.makeText(getBaseContext(), "start query", Toast.LENGTH_SHORT).show();


                for (int loop = 0; loop < swap.size(); loop++) {
                    //Toast.makeText(getBaseContext(), swap.get(loop).getCurrent_course(), Toast.LENGTH_SHORT).show();

                    if (c_course.equals(swap.get(loop).getRequired_course()) && c_section.equals(swap.get(loop).getRequired_section())
                            && r_course.equals(swap.get(loop).getCurrent_course()) && r_section.equals(swap.get(loop).getCurrent_section())) {
                        user2 = swap.get(loop).getStudent_id().toString();
                        user2ID = swap.get(loop).getUserID();
                        flag = true;
                        sts.setText("Swapper Found!");
                        //Toast.makeText(getBaseContext(), user2ID, Toast.LENGTH_SHORT).show();
                    }

                }

                sts.setVisibility(View.VISIBLE);
                // Toast.makeText(getBaseContext(), "end query", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Toast.makeText(getBaseContext(), backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });



    }

    public void swap(View v)
    {
        //Toast.makeText(getBaseContext(), String.valueOf(flag), Toast.LENGTH_SHORT).show();


        user1 = getIntent().getStringExtra("email").toString();

        //BackendlessUser us;

        if (flag)
        {
            Toast.makeText(getBaseContext(), "Congrats Match Found", Toast.LENGTH_SHORT).show();


            Backendless.initApp(this, "EDFAE2C9-0CA7-0F11-FF27-7C0BCEA73A00", "53B908DC-A5F0-1A39-FF3B-52D2AA64E400", "v1");
            //final BackendlessUser[] usr = new BackendlessUser[1];

            Backendless.UserService.findById(user2ID, new AsyncCallback<BackendlessUser>() {
                @Override
                public void handleResponse(BackendlessUser backendlessUser) {
                    BackendlessUser us = backendlessUser;

                    //sts.setText(us.getProperty("DeviceID").toString());

                    DeliveryOptions deliveryOptions = new DeliveryOptions();
                    deliveryOptions.addPushSinglecast(us.getProperty("DeviceID").toString());
                    //deliveryOptions.setPushPolicy(PushPolicyEnum.ONLY);

                    PublishOptions publishOptions = new PublishOptions();
                    publishOptions.putHeader(PublishOptions.ANDROID_TICKER_TEXT_TAG, "Swapper Found!");
                    publishOptions.putHeader(PublishOptions.ANDROID_CONTENT_TITLE_TAG, "You found a swapper!");
                    publishOptions.putHeader(PublishOptions.ANDROID_CONTENT_TEXT_TAG, "You can contact him at " + user1);

                    Backendless.Messaging.publish("default", "this is a private message!", publishOptions, deliveryOptions, new BackendlessCallback<MessageStatus>() {
                        @Override
                        public void handleResponse(MessageStatus response) {
                            //progressDialog.cancel();
                            //messageField.setText("");
                            Toast.makeText(getBaseContext(), "Message sent successfully to your swapper!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            //progressDialog.cancel();
                            Toast.makeText(Swapper.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                @Override
                public void handleFault(BackendlessFault backendlessFault) {
                    sts.setText(backendlessFault.getMessage());
                }
            });



            user1ID = Backendless.UserService.CurrentUser().getUserId();
            //sts.setText(user1ID);
            Swap obj = new Swap();
            obj.setCurrent_course(cc.getSelectedItem().toString());
            obj.setCurrent_section(cs.getSelectedItem().toString());
            obj.setRequired_course(rc.getSelectedItem().toString());
            obj.setRequired_section(rs.getSelectedItem().toString());
            obj.setStudent_id(user1);
            obj.setUserID(user1ID);


            ///start here
            obj.saveAsync(new AsyncCallback<Swap>() {

                @Override
                public void handleResponse(Swap swap) {
                    Toast.makeText(getBaseContext(), "Request Saved Successfully", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void handleFault(BackendlessFault backendlessFault) {
                    Toast.makeText(getBaseContext(), backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
                }


                //   Toast.makeText(getBaseContext(), user2, Toast.LENGTH_SHORT).show();


            });

            //sts.setText(usr[0].getProperty("DeviceID").toString());

           /*
*/


            //
            //Toast.makeText(getBaseContext(), Backendless.UserService.findById(user2ID).getProperty("DeviceID").toString(), Toast.LENGTH_SHORT).show();

        }
        else
        {


            user1ID = Backendless.UserService.CurrentUser().getUserId();
            //sts.setText(user1ID);
            Swap obj = new Swap();
            obj.setCurrent_course(cc.getSelectedItem().toString());
            obj.setCurrent_section(cs.getSelectedItem().toString());
            obj.setRequired_course(rc.getSelectedItem().toString());
            obj.setRequired_section(rs.getSelectedItem().toString());
            obj.setStudent_id(user1);
            obj.setUserID(user1ID);


            ///start here
            obj.saveAsync(new AsyncCallback<Swap>() {

                @Override
                public void handleResponse(Swap swap) {
                    Toast.makeText(getBaseContext(), "Request Saved Successfully", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void handleFault(BackendlessFault backendlessFault) {
                    Toast.makeText(getBaseContext(), backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
                }


                //   Toast.makeText(getBaseContext(), user2, Toast.LENGTH_SHORT).show();


            });
        }
    }

}


