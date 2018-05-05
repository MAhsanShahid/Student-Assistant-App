package com.student.studentassistantapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.messaging.DeliveryOptions;
import com.backendless.messaging.MessageStatus;
import com.backendless.messaging.PublishOptions;

/**
 * Created by TALHA on 2.4.16.
 */
public class StudentHome extends Activity {
    TextView tv;
    String user_id;

    //private String file = "abc.txt";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_home);




        user_id=getIntent().getStringExtra("email").toString();
        Backendless.initApp(this, BackendSettings.APPLICATION_ID, BackendSettings.ANDROID_SECRET_KEY, BackendSettings.VERSION);

        Backendless.Messaging.registerDevice("639868978616", new AsyncCallback<Void>() {
            @Override
            public void handleResponse(Void response) {
                Log.i("MY APP", "Device has been registered.");

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("MY APP", "Server reported an error - " + fault.getMessage());
            }
        });


        Intent i = getIntent();
        //TextView t = (TextView)findViewById(R.id.testttt);
        //t.setText("Hello " + i.getStringExtra("fn") + " " + i.getStringExtra("ln"));
        //tv = (TextView)findViewById(R.id.textView5s);
    }

    public void registerClass(View v)
    {
        Intent in = new Intent(this,RegisterClass.class);
        in.putExtra("email", user_id);
        startActivity(in);

    }

    public void swapper(View v)
    {
        Intent s = new Intent(this,Swapper.class);
        s.putExtra("email",user_id);
        startActivity(s);
    }

    public void addEvent(View v)
    {
        Intent s = new Intent(this,Add_Event.class);
        s.putExtra("email",user_id);
        startActivity(s);
    }


    public void view_event(View v)
    {
        Intent s = new Intent(this,View_Event.class);
        s.putExtra("email",user_id);
        startActivity(s);
    }

    public void push(View v)
    {
        Backendless.initApp(this,"EDFAE2C9-0CA7-0F11-FF27-7C0BCEA73A00", "53B908DC-A5F0-1A39-FF3B-52D2AA64E400", "v1");
        DeliveryOptions deliveryOptions = new DeliveryOptions();
        //deliveryOptions.setPushBroadcast( PushBroadcastMask.ALL );
        deliveryOptions.addPushSinglecast("99ebccab");
        //deliveryOptions.setPushPolicy(PushPolicyEnum.ONLY);

        PublishOptions publishOptions = new PublishOptions();
        publishOptions.putHeader(PublishOptions.ANDROID_TICKER_TEXT_TAG, "You just got a private push notification!");
        publishOptions.putHeader(PublishOptions.ANDROID_CONTENT_TITLE_TAG, "This is a notification title");
        publishOptions.putHeader(PublishOptions.ANDROID_CONTENT_TEXT_TAG, "Push Notifications are cool");

        Backendless.Messaging.publish("default", "this is a private message!", publishOptions, deliveryOptions, new BackendlessCallback<MessageStatus>() {
            @Override
            public void handleResponse(MessageStatus response) {
                //progressDialog.cancel();
                //messageField.setText("");
                Toast.makeText(getBaseContext(), "Message published!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                //progressDialog.cancel();
                Toast.makeText(StudentHome.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
       /* Backendless.Messaging.publish(Defaults.CHANNEL_NAME,"this is a private message!", publishOptions, deliveryOptions, new AsyncCallback<MessageStatus>() {
            @Override
            public void handleResponse(MessageStatus messageStatus) {
                Toast.makeText(getBaseContext(), "Message published!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Toast.makeText(getBaseContext(), "Message not published!", Toast.LENGTH_SHORT).show();
            }
        });
*/
    }

    public void upload(View v)
    {
        Intent i = new Intent(this,UploadBookLibrary.class);
        startActivity(i);
        /*try{/////start here
            FileInputStream fin = openFileInput(file);
            int c;
            String temp="";

            while( (c = fin.read()) != -1){
                temp = temp + Character.toString((char)c);
            }
            tv.setText(temp);
            Toast.makeText(getBaseContext(),"file read",Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        /*Bitmap photo = (Bitmap) getIntent().getExtras().get( "data" );

        Backendless.Files.Android.upload(photo,
                Bitmap.CompressFormat.PNG,
                100,
                "myphoto.png",
                "mypics",
                new AsyncCallback<BackendlessFile>() {
                    @Override
                    public void handleResponse(final BackendlessFile backendlessFile) {
                    }

                    @Override
                    public void handleFault(BackendlessFault backendlessFault) {
                        Toast.makeText(StudentHome.this, backendlessFault.toString(), Toast.LENGTH_SHORT).show();
                    }
                });*/
    }


     public void download(View v)
     {
         Intent i = new Intent(this,DownloadFile.class);
         startActivity(i);
     }


        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_filter){
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
        }
        else
        if(id == R.id.chngps)
        {
            Intent is = new Intent(this,ChangePassword.class);
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


}
