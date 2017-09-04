package com.example.edry.slepper;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by edry on 16/08/2017.
 */

public abstract class OnCallDatabase extends RealTimeDatabase {

    protected static final String OnCallincomingCallNumPath = "incomingCallNum";

    protected static final String KeepAlivePath = "KeepAlive";
    protected static final String KeepAliveStatusPath = "Status";

    protected static final String ActiveUsersPath = "ActiveUsers";
    protected static final String expireDatePath = "ExpireDate";
    protected static final String RingerLevelPath = "RingerLevel";
    protected static final String dndPath = "DND-Speaker";

    protected static final String smsMassageOnNull = "I'm inviting you to download my app";

    protected static String BussyStatus = "Bussy";
    protected static String availableStatus = "Available";

    protected static String PhoneNum;

    protected DatabaseReference Cref;

    private boolean IsRecNotification = false;

    protected boolean PeerUserrResult = false;

    Context myContex;

    public OnCallDatabase(Context contex, String PhoneNum) {

        super(contex, BussyStatus);

        this.PhoneNum = PhoneNum;

        Cref = FirebaseDatabase.getInstance().getReference();

        myContex = contex;
    }

    protected abstract boolean  handleServerResponse(String Response);


    public  void  CheckDataBaseState()
    {
        this.setRealTimeDatabaseChanges(Cref.child(ActiveUsersPath));



        DatabaseReference peer = Cref.child(KeepAlivePath).child(PhoneNum).child(KeepAliveStatusPath);

        peer.addListenerForSingleValueEvent(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot)
            {
                IsRecNotification = handleServerResponse(dataSnapshot.getValue(String.class));

            }


            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public boolean getisRecNotification()
    {
        return IsRecNotification;
    }





    protected int CaseResponse(String Response , String ansToCompare)
    {
        if(Response == null)
            return 0;
        else
            if(Response.equals(ansToCompare))
                return 1;
        else
            return 2;
    }


}
