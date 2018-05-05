package com.student.studentassistantapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TALHA on 5.5.16.
 */
public class View_Event extends Activity {

    List<String> titles;
    List<Event> list;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_event);
        listView = (ListView) findViewById(R.id.list);




        BackendlessDataQuery query = new BackendlessDataQuery();
        Event.findAsync(query, new AsyncCallback<BackendlessCollection<Event>>() {
            @Override
            public void handleResponse(BackendlessCollection<Event> eventBackendlessCollection) {
                list = eventBackendlessCollection.getCurrentPage();

                titles = new ArrayList<String>();

                for (int i = 0; i < list.size(); i++) {
                    titles.add(list.get(i).getTitle());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1,  titles);
                listView.setAdapter(adapter);
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Toast.makeText(getBaseContext(), backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}
