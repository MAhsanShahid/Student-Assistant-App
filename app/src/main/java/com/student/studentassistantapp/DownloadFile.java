package com.student.studentassistantapp;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.FileInfo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by TALHA on 11.5.16.
 */
public class DownloadFile extends Activity {
    ArrayList<String> dn;
    ListView lv;
    Context c;
    List<Enrolment> course;
    List<String>cr;
    ArrayList<String> ur;
    private ProgressDialog pDialog;
    public static final int progress_bar_type = 0;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    Spinner select;

    //private String file = "abc.txt";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        c = this;
        dn = new ArrayList<String>();
        ur = new ArrayList<String>();
        cr = new ArrayList<String>();


        lv = (ListView) findViewById(R.id.mobile_list);
        /*select = (Spinner)findViewById(R.id.selection);

        final BackendlessUser us = Backendless.UserService.CurrentUser();

        BackendlessDataQuery q1 = new BackendlessDataQuery();
        Enrolment.findAsync(q1, new AsyncCallback<BackendlessCollection<Enrolment>>() {
            @Override
            public void handleResponse(BackendlessCollection<Enrolment> enrolmentBackendlessCollection) {
                course = enrolmentBackendlessCollection.getCurrentPage();
                for(int i = 0; i < course.size(); i++)
                {
                    if(us.getEmail().equals(course.get(i).getStudent_id()))
                    {
                        String[] ab = course.get(i).getClass_name().split("CS",2);
                        cr.add(ab[0]);
                    }
                }
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {

            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_items, cr);
        select.setAdapter(adapter);


        String abc = select.getSelectedItem().toString();
        abc = abc.replace(" ","+");


        Toast.makeText(DownloadFile.this, abc, Toast.LENGTH_SHORT).show();*/
        //String[] mobileArray = null;//{"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};

        Backendless.Files.listing("Book+Library", new AsyncCallback<BackendlessCollection<FileInfo>>() {
            @Override
            public void handleResponse(BackendlessCollection<FileInfo> response) {
                Iterator<FileInfo> filesIterator = response.getCurrentPage().iterator();

                while (filesIterator.hasNext()) {
                    FileInfo file = filesIterator.next();
                    String URL = file.getURL();
                    String publicURL = file.getPublicUrl();
                    Date createdOn = new Date(file.getCreatedOn());
                    String name = file.getName();

                    dn.add(name);
                    ur.add(publicURL);

                }

                Toast.makeText(DownloadFile.this, "Files Added", Toast.LENGTH_SHORT).show();

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, dn);
                lv.setAdapter(adapter);
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Toast.makeText(DownloadFile.this, backendlessFault.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                new DownloadFileFromURL(dn.get(position)).execute(ur.get(position));

            }
        });


    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type: // we set this to 0
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
    }

    /**
     * Background Async Task to download file
     * */
    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Bar Dialog
         * */
        String nm;

        public DownloadFileFromURL(String name)
        {
            nm = name;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(progress_bar_type);
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();

                // this will be useful so that you can show a tipical 0-100%
                // progress bar
                int lenghtOfFile = conection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(),
                        8192);

                File f = new File(Environment.getExternalStoragePublicDirectory("Download").getPath()+ "/" + nm);
                f.createNewFile();

                verifyStoragePermissions(DownloadFile.this);
                OutputStream output = new FileOutputStream(f);//Environment.getExternalStorageDirectory().toString() + "/Download");

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        /**
         * Updating progress bar
         * */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            dismissDialog(progress_bar_type);

        }

    }
}
