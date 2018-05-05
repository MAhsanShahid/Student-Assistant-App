package com.student.studentassistantapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

/**
 * Created by TALHA on 5.5.16.
 */
public class Add_Event extends Activity {

    private Spinner day,month,year, priority;
    private EditText title;
    private    EditText desc;

    private String date;
    private  String t;
    private String de;
    private String pri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);
        String[] d={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        String[] m={"January" , "February" , "March", "April","May","June","July","August","September","October","November","December"};
        String [] y={"2010", "2011","2012","2013","2014","2015","2016","2017","2018","2019","2020"};
        String []pri ={"high", "medium","low"};

        day = (Spinner) findViewById(R.id.day);
        month = (Spinner) findViewById(R.id.month);
        year =(Spinner)findViewById(R.id.year);
        priority =(Spinner)findViewById(R.id.pri);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_items, d);
        day.setAdapter(adapter1);


        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_items, m);
        month.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_items, y);
        year.setAdapter(adapter3);

        ArrayAdapter<String> adapter4= new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_items, pri);
        priority.setAdapter(adapter4);

        title = (EditText) findViewById(R.id.title);
        desc = (EditText) findViewById(R.id.desc);




    }// oncreate


    public void event_add(View v)
    {
        String d = day.getSelectedItem().toString();
        String m= month.getSelectedItem().toString();
        String y = year.getSelectedItem().toString();
        String time= d.concat("-" + m + "-" + y);


        String pri = priority.getSelectedItem().toString();

        t= title.getText().toString();
        de =desc.getText().toString();


        // Toast.makeText(getBaseContext(),pri,Toast.LENGTH_SHORT).show();


        Event obj= new Event();

        obj.setDueDate(time);
        obj.setTitle(t);
        obj.setDecsciption(de);
        obj.setPriority(pri);

        obj.saveAsync(new AsyncCallback<Event>() {
            @Override
            public void handleResponse(Event event) {
                Toast.makeText(getBaseContext(), "Event Posted Sucessfully!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Toast.makeText(getBaseContext(), backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}
