package com.student.studentassistantapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessException;
import com.backendless.exceptions.BackendlessFault;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TALHA on 30.3.16.
 */
public class SignUP extends Activity {

    EditText fn,ln,em,ps,tp;
    RadioGroup rg;
    RadioButton rb;
    Spinner spinner;
    String utp;
    List<String> ut;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);


        String appVersion = "v1";
        Backendless.initApp(this, "EDFAE2C9-0CA7-0F11-FF27-7C0BCEA73A00", "53B908DC-A5F0-1A39-FF3B-52D2AA64E400", appVersion);


        fn = (EditText)findViewById(R.id.fn);
        ln = (EditText)findViewById(R.id.ln);
        em = (EditText)findViewById(R.id.em);
        ps = (EditText)findViewById(R.id.ps);
       // tp = (EditText)findViewById(R.id.tp);
        //ut = (EditText)findViewById(R.id.)
        rg = (RadioGroup)findViewById(R.id.radioGroup);
        ut = new ArrayList<String>();
        ut.add("Teacher");
        ut.add("Student");

        try {
            spinner = (Spinner) findViewById(R.id.UserType);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_items, ut);
            spinner.setAdapter(adapter);

        }
        catch (Exception e)
        {
            Log.i("my error: ",e.getMessage());
        }





    }

    public void sign_up(View v)
    {

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


        utp = spinner.getSelectedItem().toString();

        try {
            int selectedId = rg.getCheckedRadioButtonId();
            rb = (RadioButton) findViewById(selectedId);

            BackendlessUser user = new BackendlessUser();
            user.setEmail(em.getText().toString());
            user.setPassword(ps.getText().toString());
            user.setProperty("firstname", fn.getText().toString());
            user.setProperty("lastname", ln.getText().toString());
            user.setProperty("gender", rb.getText().toString());
            user.setProperty("usertype", utp);
            user.setProperty("DeviceID", Build.SERIAL);

            Backendless.UserService.register(user, new BackendlessCallback<BackendlessUser>() {
                @Override
                public void handleResponse(BackendlessUser backendlessUser) {
                    Log.i("Registration", backendlessUser.getEmail() + " successfully registered");
                    Toast.makeText(SignUP.this, backendlessUser.getEmail() + " successfully registered!", Toast.LENGTH_SHORT).show();
                }
            });

            Intent i = new Intent(this, Login.class);
            startActivity(i);

        }
        catch (BackendlessException e)
        {
            Log.i("abError: ",e.getMessage());
            //Toast.makeText(SignUP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        //Toast.makeText(MainActivity.this, radioSexButton.getText(), Toast.LENGTH_SHORT).show();
    }


}
