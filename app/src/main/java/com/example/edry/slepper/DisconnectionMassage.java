package com.example.edry.slepper;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by edry on 21/08/2017.
 */

public class DisconnectionMassage extends RealTimeDatabase {

    private DatabaseReference Cref;
    private static final String KeepAliveStatusPath = "Status";

    private static final String status = "Off";


    public DisconnectionMassage(Context contex) {
        super(contex, status);
        Cref = FirebaseDatabase.getInstance().getReference().child("KeepAlive");
        setRealTimeDatabaseChanges(Cref);
         }


}

