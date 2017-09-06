package com.example.edry.slepper;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by edry on 21/08/2017.
 * Call for this class update the peer's entry on database
 * inform the peer that the call is importent
 */

public class SignalPeerMassage extends RealTimeDatabase {

    private static String status = "Bussy";

    protected static final String ActiveUsersPath = "ActiveUsers";
    protected static final String pushEmnotificatioPath = "Emergency";
    protected static final String IncomingCallNumPath = "incomingCallNum";


    protected static final String YesEmergencyNotification = "Yes";


    private DatabaseReference Cref;


    protected static String PhoneNum;

    public SignalPeerMassage(String PhoneNum )
    {

        super(status);

        this.PhoneNum = PhoneNum;

        Cref = FirebaseDatabase.getInstance().getReference().child(ActiveUsersPath).child(PhoneNum);

        DatabaseReference incomingPhone =  Cref.child(IncomingCallNumPath);

        incomingPhone.setValue(this.myPhoneNumber);

        DatabaseReference Emergency =  Cref.child(pushEmnotificatioPath);

        Emergency.setValue(YesEmergencyNotification);

    }
}
