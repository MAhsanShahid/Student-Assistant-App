package com.student.studentassistantapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

/**
 * Created by TALHA on 30.3.16.
 */
public class Login extends Activity {

    EditText un,ps;
    ProgressBar pg;
    TextView er;
    Context c = this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Backendless.initApp(this, BackendSettings.APPLICATION_ID, BackendSettings.ANDROID_SECRET_KEY, BackendSettings.VERSION);

        un = (EditText)findViewById(R.id.un);
        ps = (EditText)findViewById(R.id.pss);
        er = (TextView)findViewById(R.id.error);
        pg = (ProgressBar)findViewById(R.id.progressBar);
        pg.setVisibility(View.INVISIBLE);
        er.setVisibility(View.INVISIBLE);

        if(Backendless.UserService.isValidLogin()) {
            if (Backendless.UserService.CurrentUser().getProperty("usertype").toString().equals("Student")) {
                Intent i = new Intent("android.intent.action.stdhome");
                startActivity(i);
                //finish();
            } else {
                Intent i = new Intent("android.intent.action.teacherhome");
                startActivity(i);
                //finish();
            }
        }
    }


    public void lg_click(final View v)
    {
         // where to get the argument values for this call
        //BackendlessUser user;
        final View vs = v;
        pg.setVisibility(vs.VISIBLE);


        //Toast.makeText(Login.this, ps.getText().toString(), Toast.LENGTH_SHORT).show();
        Backendless.UserService.login(un.getText().toString(), ps.getText().toString(), new AsyncCallback<BackendlessUser>() {
            public void handleResponse(BackendlessUser user) {

                if (user.getProperty("usertype").toString().equals("Student")) {
                    Intent i = new Intent("android.intent.action.stdhome");
                    i.putExtra("fn", user.getProperty("firstname").toString());
                    i.putExtra("ln", user.getProperty("lastname").toString());
                    i.putExtra("email", user.getEmail());

                    startActivity(i);// user has been logged in
                    pg.setVisibility(vs.INVISIBLE);

                } else if (user.getProperty("usertype").toString().equals("Teacher")) {
                    Intent i = new Intent("android.intent.action.teacherhome");
                    //i.putExtra("User",user);
                    i.putExtra("fn", user.getProperty("firstname").toString());
                    i.putExtra("ln", user.getProperty("lastname").toString());
                    i.putExtra("Teacherid", user.getObjectId());
                    i.putExtra("Email", user.getEmail());
                    i.putExtra("Password", user.getPassword());

                    startActivity(i);
                    pg.setVisibility(vs.INVISIBLE);
                }
            }

            public void handleFault(BackendlessFault fault) {

                er.setText(fault.toString());
                er.setVisibility(vs.VISIBLE);
                pg.setVisibility(vs.INVISIBLE);
                // login failed, to get the error code call fault.getCode()
            }
        });

        /*try
        {
            user = Backendless.UserService.login(un.getText().toString(), ps.getText().toString());
            Intent i = new Intent(this,StudentHome.class);
            startActivity(i);

            TextView er = (TextView)findViewById(R.id.error);
            er.setVisibility(v.VISIBLE);
        }
        catch( BackendlessException exception )
        {
            // login failed, to get the error code, call exception.getFault().getCode()
        }*/
    }
}
