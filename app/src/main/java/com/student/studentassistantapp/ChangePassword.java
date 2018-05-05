package com.student.studentassistantapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

/**
 * Created by TALHA on 15.4.16.
 */
public class ChangePassword extends Activity {
    EditText op,np,cp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);


        np = (EditText)findViewById(R.id.np);
        cp = (EditText)findViewById(R.id.cp);

        String appVersion = "v1";
        Backendless.initApp(this,"EDFAE2C9-0CA7-0F11-FF27-7C0BCEA73A00", "53B908DC-A5F0-1A39-FF3B-52D2AA64E400", appVersion);
    }

    public void SavePassword(View v)
    {
        //Toast.makeText(getBaseContext(), Backendless.UserService.CurrentUser().getProperty("firstname").toString(), Toast.LENGTH_SHORT).show();

            if (Backendless.UserService.isValidLogin()) {
                if(np.getText().toString().equals(cp.getText().toString())) {
                    Backendless.UserService.CurrentUser().setPassword(np.getText().toString());
                    Backendless.UserService.update(Backendless.UserService.CurrentUser(), new AsyncCallback<BackendlessUser>() {
                        public void handleResponse(BackendlessUser user) {
                            // user has been updated
                            Toast.makeText(getBaseContext(), "Password Updated Successfully!", Toast.LENGTH_SHORT).show();
                        }

                        public void handleFault(BackendlessFault fault) {
                            // user update failed, to get the error code call fault.getCode()
                            Toast.makeText(getBaseContext(), fault.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(getBaseContext(), "Confirm Password is not same as new Password!", Toast.LENGTH_SHORT).show();
                }

            }
            else
            {
                Toast.makeText(getBaseContext(), "No user Logged In!", Toast.LENGTH_SHORT).show();
            }
        }


}
