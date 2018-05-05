package com.student.studentassistantapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.backendless.Backendless;

public class MainActivity extends Activity {

    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String appVersion = "v1";
        Backendless.initApp(this, "EDFAE2C9-0CA7-0F11-FF27-7C0BCEA73A00", "53B908DC-A5F0-1A39-FF3B-52D2AA64E400", appVersion);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                if(Backendless.UserService.isValidLogin())
                {
                    if(Backendless.UserService.CurrentUser().getProperty("usertype").toString().equals("Student"))
                    {
                        Intent i = new Intent("android.intent.action.stdhome");
                        startActivity(i);
                        //finish();
                    }
                    else {
                        Intent i = new Intent("android.intent.action.teacherhome");
                        startActivity(i);
                        //finish();
                    }
                }
                else
                {
                    Intent i = new Intent(MainActivity.this, Homes.class);

                    startActivity(i);
                    //finish();
                }

                // close this activity

            }
        }, SPLASH_TIME_OUT);
    }

}
