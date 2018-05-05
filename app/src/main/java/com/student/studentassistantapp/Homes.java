package com.student.studentassistantapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.backendless.Backendless;

/**
 * Created by TALHA on 29.3.16.
 */
public class Homes extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homes_screen);

    }

    public void login_click(View v)
    {
        Intent i = new Intent(this,Login.class);
        startActivity(i);
    }

    public void sign_click(View v)
    {
        Intent i = new Intent(this,SignUP.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        //Display alert message when back button has been pressed
        if(Backendless.UserService.isValidLogin())
        {
            if(Backendless.UserService.CurrentUser().getProperty("usertype").toString().equals("Student"))
            {
                Intent i = new Intent(this,StudentHome.class);
                startActivity(i);
            }
            else {
                Intent i = new Intent(this,TeacherHome.class);
                startActivity(i);
            }
        }
        else
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();


        }

    }


}
