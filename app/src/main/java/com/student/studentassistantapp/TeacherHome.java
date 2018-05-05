package com.student.studentassistantapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by TALHA on 8.4.16.
 */
public class TeacherHome extends Activity {

    static TextView tx;
    Intent i;
    String userid;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    protected void onCreate(Bundle savedInstanceState) {

        String appVersion = "v1";
        Backendless.initApp(this, "EDFAE2C9-0CA7-0F11-FF27-7C0BCEA73A00", "53B908DC-A5F0-1A39-FF3B-52D2AA64E400", appVersion);

        tx = (TextView)findViewById(R.id.ft);
        if (!Backendless.UserService.isValidLogin()) {
            Intent i = new Intent(this, Homes.class);
            startActivity(i);
            finish();

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_home);


        i = getIntent();
        userid = i.getStringExtra("Email");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void addClass(View v) {
        Intent in = new Intent(this, AddClass.class);
        in.putExtra("Teach", userid);
        startActivity(in);
    }


    public void uploadResource(View v) {
        Intent in = new Intent(this, UploadResource.class);
        in.putExtra("Teach", userid);
        startActivity(in);
    }

    /////////////start here
    public void download(View v) throws IOException {
        BackendlessFile file = new BackendlessFile("https://api.backendless.com/EDFAE2C9-0CA7-0F11-FF27-7C0BCEA73A00/v1/files/media/MY%2BLatest%2BCV-Resume.docx");
        Toast.makeText(TeacherHome.this, "File start download", Toast.LENGTH_SHORT).show();
        downloadFile(file);
        Toast.makeText(TeacherHome.this, "File downloaded", Toast.LENGTH_SHORT).show();
    }


    public void downloadFile(BackendlessFile backendlessFile) throws IOException
    {

        Toast.makeText(TeacherHome.this, "File accepts", Toast.LENGTH_SHORT).show();
        URL url = new URL( backendlessFile.getFileURL() );
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if( responseCode == HttpURLConnection.HTTP_OK )
        {
            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();

            // opens an output stream to save into file
            OutputStream outputStream = System.out;

            int bytesRead = -1;
            byte[] buffer = new byte[ 4096 ];

            Toast.makeText(TeacherHome.this, "File recieve", Toast.LENGTH_SHORT).show();
            System.out.println( "File content is:\n===========================" );

            while( (bytesRead = inputStream.read( buffer )) != -1 )
                outputStream.write( buffer, 0, bytesRead );

            System.out.println("===========================");

            outputStream.close();
            inputStream.close();

            System.out.println("File downloaded");
            tx.setText("File downloaded");
            //Toast.makeText(TeacherHome.this, "File downloaded", Toast.LENGTH_SHORT).show();
        }
        else
        {
            System.out.println( "No file to download. Server replied HTTP code: " + responseCode );
        }
        httpConn.disconnect();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_filter) {

            Backendless.UserService.logout(new AsyncCallback<Void>() {
                public void handleResponse(Void response) {
                    // user has been logged out
                    Intent i = new Intent("android.intent.action.homes");
                    startActivity(i);
                    Toast.makeText(getBaseContext(), "User is Logged out!", Toast.LENGTH_SHORT).show();

                }

                public void handleFault(BackendlessFault fault) {
                    // something went wrong and logout failed, to get the error code call fault.getCode()
                    Toast.makeText(getBaseContext(), "Logout not successful!", Toast.LENGTH_SHORT).show();
                }
            });

            return true;
        } else if (id == R.id.chngps) {
            Intent is = new Intent(this, ChangePassword.class);
            startActivity(is);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        //  preparation code here
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "TeacherHome Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.student.studentassistantapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "TeacherHome Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.student.studentassistantapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
