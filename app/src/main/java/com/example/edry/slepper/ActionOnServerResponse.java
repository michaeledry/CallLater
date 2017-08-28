package com.example.edry.slepper;

import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by edry on 21/08/2017.
 */

public class ActionOnServerResponse {

    private String peerPhoneNumber;
    String jobDone = "Mute";
    protected static final String YesEmergencyNotification = "Yes";
    Context myContext;

    DatabaseReference refStatus ;
    ValueEventListener Listener;



    public ActionOnServerResponse(Context context , String peerPhoneNumber) {
        this.peerPhoneNumber = peerPhoneNumber;
        this.myContext = context;
    }

    public void  openPopUpForUI()
    {
        Intent newIntent = new Intent(myContext, popupForServerUpdate.class);
        newIntent.putExtra("phoneNum", peerPhoneNumber);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        System.out.println("Flow: open popup: ") ;
        myContext.startActivity(newIntent);
    }

    public void setMaxVolume()
    {
        System.out.println("Flow: setMaxVolume : Accept incoming massage from: "+ peerPhoneNumber) ;
        MyPhoneState TakeActionOnCall = new MyPhoneState();
        TakeActionOnCall.onCallStateChanged(myContext,1,null);
    }

    public void SendSms(String Massage)
    {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(peerPhoneNumber, null,Massage, null, null);
    }


    public void SendMail( String Email , String Massage)
    {

    }

    public void ListenForCallStatus(DatabaseReference Phone, DatabaseReference status)
    {

        refStatus = status;//FirebaseDatabase.getInstance().getReference().child("ActiveUsers").child("0507577442").child("Emergency");
        ListenerForData();
        refStatus.addValueEventListener(Listener);
    }


    public String isjobDone()
    {
        return jobDone;
    }


    private void ListenerForData()
    {
        System.out.println("ActionHandler.ListenForCallStatus : ListenerForData " );

        Listener = new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                System.out.println("Flow: ListenForCallStatus : "+dataSnapshot.getValue(String.class));


                if(dataSnapshot.getValue(String.class).equals(YesEmergencyNotification))
                {
                    //setMaxVolume();

                    jobDone = "MaxVol";
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        };

    }

    public void removeListener()
    {
        if (Listener != null)
            refStatus.removeEventListener(Listener);
        else
            System.out.println("Flow: ServerConnection : removeListener, null " ) ;

    }
}
