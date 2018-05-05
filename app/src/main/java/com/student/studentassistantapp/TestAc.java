package com.student.studentassistantapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;

import java.io.File;

/**
 * Created by TALHA on 25.4.16.
 */
public class TestAc extends Activity{

    EditText editTextFileName,editTextData;
    Button saveButton,readButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        editTextFileName=(EditText)findViewById(R.id.editText1);
        editTextData=(EditText)findViewById(R.id.editText2);
        saveButton=(Button)findViewById(R.id.button1);
        readButton=(Button)findViewById(R.id.button2);

       /* //Performing Action on Read Button
        saveButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                String filename=editTextFileName.getText().toString();
                String data=editTextData.getText().toString();

                FileOutputStream fos;
                try {
                    fos = openFileOutput(filename, Context.MODE_PRIVATE);
                    //default mode is PRIVATE, can be APPEND etc.
                    fos.write(data.getBytes());
                    fos.close();

                    Toast.makeText(getApplicationContext(),filename + " saved",
                            Toast.LENGTH_LONG).show();


                } catch (FileNotFoundException e) {e.printStackTrace();}
                catch (IOException e) {e.printStackTrace();}

            }

        });

        //Performing Action on Read Button
        readButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                String filename=editTextFileName.getText().toString();
                StringBuffer stringBuffer = new StringBuffer();
                try {
                    //Attaching BufferedReader to the FileInputStream by the help of InputStreamReader
                    BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                            openFileInput(filename)));
                    String inputString;
                    //Reading data line by line and storing it into the stringbuffer
                    while ((inputString = inputReader.readLine()) != null) {
                        stringBuffer.append(inputString + "\n");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Displaying data on the toast
                Toast.makeText(getApplicationContext(), stringBuffer.toString(),
                        Toast.LENGTH_LONG).show();

            }

        });*/
    }


    public void read(View v)
    {
        /*Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

        intent.setType("text/csv/docx");

        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), 0);

        } catch (android.content.ActivityNotFoundException ex) {
            ex.printStackTrace();
        }*/

        Intent chooser = new Intent(Intent.ACTION_GET_CONTENT);
        //chooser.setType("file/*");
        Uri uri = Uri.parse(Environment.getDataDirectory().getPath().toString());
        chooser.addCategory(Intent.CATEGORY_OPENABLE);
        chooser.setDataAndType(uri, "*/*");
// startActivity(chooser);
        try {
            startActivityForResult(chooser, 0);
        }
        catch (android.content.ActivityNotFoundException ex)
        {
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }


    public void  onActivityResult(int requestCode, int resultCode, Intent data) {

        final TextView tv = (TextView)findViewById(R.id.dsf);

        switch (requestCode) {
           case 0: {
               try {
                   //FileInputStream fileInputStream = null;

                   String FilePath = data.getData().getPath();

                   File file = new File(FilePath);

                /*byte[] bFile = new byte[(int) file.length()];

                try {
                    //convert file into array of bytes
                    fileInputStream = new FileInputStream(file);
                    fileInputStream.read(bFile);
                           fileInputStream.close();

                           for (int i = 0; i < bFile.length; i++) {
                               System.out.print((char)bFile[i]);
                           }

                           System.out.println("Done");
                           Toast.makeText(getBaseContext(),FilePath,Toast.LENGTH_SHORT).show();
                           tv.setText("file read successfuly");
                       }catch(Exception e){
                           e.printStackTrace();
                           tv.setText(e.getMessage()+"   "+FilePath);//Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                       }*/


                               Backendless.Files.upload(file, "media", new AsyncCallback<BackendlessFile>() {
                           @Override
                           public void handleResponse(BackendlessFile backendlessFile) {
                               final BackendlessFile b = backendlessFile;
                           Backendless.Persistence.save(b, new BackendlessCallback<BackendlessFile>() {
                               @Override
                               public void handleResponse(BackendlessFile backendlessFile) {
                                /*Intent data = new Intent();
                                data.putExtra( "data", b.getFileURL() );
                                setResult( RESULT_OK, data );
                                finish();*/
                                   tv.setText("File uploaded successfuly");

                               }
                           });
                           Toast.makeText(getBaseContext(), "file uploaded", Toast.LENGTH_SHORT).show();
                       }

                       @Override
                       public void handleFault(BackendlessFault backendlessFault) {
                           Toast.makeText(getBaseContext(), backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();

                       }
                   });


               }
               catch(Exception e)
               {
                   tv.setText(e.getMessage());
               }

        }




        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }
}
