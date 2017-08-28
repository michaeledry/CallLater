package com.example.edry.slepper;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by edry on 16/08/2017.
 */

public class keepAlive extends RealTimeDatabase {

    private DatabaseReference Cref;
    private static final String KeepAliveStatusPath = "Status";


    private static final String status = "On";

    public keepAlive(Context contex) {

        super(contex , status );
        Cref = FirebaseDatabase.getInstance().getReference().child("KeepAlive");
    }

    public void pushKeepAliveUpdateToServer()
    {
        setRealTimeDatabaseChanges(Cref);
    }
    public String getPhoneNum(){return this.myPhoneNumber;};


}
