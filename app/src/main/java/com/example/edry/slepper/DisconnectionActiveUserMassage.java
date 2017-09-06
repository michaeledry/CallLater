package com.example.edry.slepper;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by edry on 21/08/2017.
 */

public class DisconnectionActiveUserMassage extends RealTimeDatabase {


    protected static final String ActiveUsersPath = "ActiveUsers";
    protected static final String IncomingCallNumPath = "incomingCallNum";
    protected static final String pushEmnotificatioPath = "Emergency";



    //expireDate
    //


    public DisconnectionActiveUserMassage( String status) {

        super(status);

        DatabaseReference statusRef = FirebaseDatabase.getInstance().getReference().child(ActiveUsersPath);

        setRealTimeDatabaseChanges(statusRef);

        DatabaseReference incomingRef = FirebaseDatabase.getInstance().getReference().child(ActiveUsersPath).child(this.myPhoneNumber);

        incomingRef.child(IncomingCallNumPath).setValue("Nill");

        incomingRef.child(pushEmnotificatioPath).setValue("No");

        System.out.println("Flow: DisconnectionActiveUserMassage : masggs  " );


    }
}
