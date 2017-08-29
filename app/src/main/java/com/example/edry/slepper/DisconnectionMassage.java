package com.example.edry.slepper;

import android.content.Context;
import android.media.AudioManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by edry on 21/08/2017.
 */

public class DisconnectionMassage extends RealTimeDatabase {

    private DatabaseReference Cref;
    private static final String KeepAliveStatusPath = "Status";

    private static final String status = "Off";
    private static final String NA = "NOT AVAILABLE";


    protected static final String expireDatePath = "ExpireDate";
    protected static final String RingerLevelPath = "RingerLevel";
    protected static final String dndPath = "DND-Speaker";
    protected static final String KeepAlivePath = "KeepAlive";



    public DisconnectionMassage(Context contex)
    {
        super(contex, status);
        Cref = FirebaseDatabase.getInstance().getReference();
        setRealTimeDatabaseChanges(Cref.child("KeepAlive"));
        updateRingerLevelEntry();
        updatedndLevelEntry();
    }


    private void  updateRingerLevelEntry()
    {
        Cref.child(KeepAlivePath).child(myPhoneNumber).child(RingerLevelPath).setValue(NA);
    }

    private void  updatedndLevelEntry()
    {

        Cref.child(KeepAlivePath).child(myPhoneNumber).child(dndPath).setValue(NA);

    }



}

