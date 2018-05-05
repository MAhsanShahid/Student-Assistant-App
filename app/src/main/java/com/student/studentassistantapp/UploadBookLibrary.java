package com.student.studentassistantapp;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;

import java.io.File;

/**
 * Created by TALHA on 6.5.16.
 */
public class UploadBookLibrary extends Activity {

    String FilePath;
    EditText files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_book_library);
        files = (EditText)findViewById(R.id.uplbook);
    }

    public void BrowseFile(View v)
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

    public void UploadFile(View v)
    {
        File file = new File(FilePath);
        Backendless.Files.upload(file, "Book Library", new AsyncCallback<BackendlessFile>() {
            @Override
            public void handleResponse(final BackendlessFile backendlessFile) {
                Toast.makeText(getBaseContext(), "File Uploaded Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Toast.makeText(UploadBookLibrary.this, backendlessFault.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
