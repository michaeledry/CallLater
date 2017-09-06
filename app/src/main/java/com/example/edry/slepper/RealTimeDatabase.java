package com.example.edry.slepper;

import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.content.Intent;
import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.media.AudioManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by edry on 16/08/2017.
 */

public abstract class RealTimeDatabase {


    protected static String myStatusPath = "Status";
    protected static String myPhoneNumber;
    protected String status;
    FirebaseUser user;




    public RealTimeDatabase( String status )

    {
        user = FirebaseAuth.getInstance().getCurrentUser();
        this.status = status;
        myPhoneNumber = "0" + user.getPhoneNumber().substring(4);


    }


    protected void setRealTimeDatabaseChanges(DatabaseReference ref)
    {

        ref.child(myPhoneNumber).child(myStatusPath).setValue(status);

        System.out.println("Flow: RealTimeDatabase : setRealTimeDatabaseChanges:  " + myPhoneNumber+ ":"+myStatusPath +  "=" +status  );

    }

    protected void ChangeStatus(String newStatus, DatabaseReference ref)
    {
        status = newStatus;
        setRealTimeDatabaseChanges(ref);
    }



}
