package com.student.studentassistantapp;

import com.backendless.Backendless;
import com.backendless.messaging.DeliveryOptions;
import com.backendless.messaging.PublishOptions;
import com.backendless.messaging.PushPolicyEnum;

import java.util.HashMap;

/**
 * Created by TALHA on 8.5.16.
 */
public class MainPush {
    public static void main(String [] args) throws Throwable
    {
        Backendless.initApp("EDFAE2C9-0CA7-0F11-FF27-7C0BCEA73A00", "53B908DC-A5F0-1A39-FF3B-52D2AA64E400", "v1");
        PublishOptions pb = new PublishOptions();
        HashMap<String,String> headers = new HashMap<String,String>();
        headers.put("android-ticker-text","Push For Android");
        headers.put("android-content-title","Urgent Message");
        headers.put("android-content-text", "You Reciecved an urgent notification.");
        pb.setHeaders(headers);


        DeliveryOptions dp = new DeliveryOptions();
        dp.setPushPolicy(PushPolicyEnum.ONLY);

        Backendless.Messaging.publish("Talha Chaudhry",pb,dp);

    }

}
