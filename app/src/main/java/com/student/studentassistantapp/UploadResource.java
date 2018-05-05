package com.student.studentassistantapp;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;
import com.backendless.persistence.BackendlessDataQuery;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TALHA on 6.5.16.
 */
public class UploadResource extends Activity{

    String FilePath;
    EditText files;
    String user;
    List<CourseClass> classes; // data from course class
    List<String> list1; // contain class names
    Spinner crs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_res);

        files = (EditText)findViewById(R.id.uplbook);
        user = getIntent().getStringExtra("Teach");
        crs = (Spinner)findViewById(R.id.crsd);
        list1 = new ArrayList<String>();

        String appVersion = "v1";
        Backendless.initApp(this, "EDFAE2C9-0CA7-0F11-FF27-7C0BCEA73A00", "53B908DC-A5F0-1A39-FF3B-52D2AA64E400", appVersion);


        BackendlessDataQuery query1 = new BackendlessDataQuery();
        CourseClass.findAsync(query1, new AsyncCallback<BackendlessCollection<CourseClass>>() {
            @Override
            public void handleResponse(BackendlessCollection<CourseClass> courseBackendlessCollection) {
                classes= courseBackendlessCollection.getCurrentPage();


                for (int i = 0; i < classes.size(); i++) {
                    if(classes.get(i).getTeacher_id().equals(user)) {
                        list1.add(classes.get(i).getCourse_name());
                    }

                }


                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_items, list1);
                //adapter.setDropDownViewResource(R.layout.spinner_dropdown);
                crs.setAdapter(adapter);

            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Toast.makeText(getBaseContext(), backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void browse(View v)
    {
        Intent chooser = new Intent(Intent.ACTION_GET_CONTENT);
        //chooser.setType("file/*");
        Uri uri = Uri.parse(Environment.getDataDirectory().getPath().toString());
        chooser.addCategory(Intent.CATEGORY_OPENABLE);
        chooser.setDataAndType(uri, "*/*");
// startActivity(chooser);
        try {
            startActivityForResult(chooser, 0);
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case 0: {
                try {
                    FilePath = data.getData().getPath();
                    files.setText(FilePath);

                } catch (Exception e) {
                    Log.i("My Error: ", e.getMessage());
                }

            }


        }
    }


    public void Upload_File(View v)
    {
        File file = new File(FilePath);
        Backendless.Files.upload(file, crs.getSelectedItem().toString(), new AsyncCallback<BackendlessFile>() {
            @Override
            public void handleResponse(final BackendlessFile backendlessFile) {
                Toast.makeText(getBaseContext(), "File Uploaded Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Toast.makeText(UploadResource.this, backendlessFault.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
