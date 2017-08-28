package com.example.edry.slepper;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by edry on 21/08/2017.
 */

public class OffUserMassge extends RealTimeDatabase {


    private static final String ActiveUsersPath = "ActiveUsers";
    private static final String status = "Offline";




    private DatabaseReference Cref;


    public OffUserMassge(Context contex) {
        super(contex, status);
        Cref = FirebaseDatabase.getInstance().getReference().child(ActiveUsersPath);
        setRealTimeDatabaseChanges(Cref);
    }
}
