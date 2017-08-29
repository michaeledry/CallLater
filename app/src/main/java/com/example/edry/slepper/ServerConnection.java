package com.example.edry.slepper;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.media.AudioManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ServerConnection extends IntentService {

    private static boolean Active = false;


    private String PhoneState;
    private String CallNumber;


    public ServerConnection() {
        super("ServerConnection");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Flow: ServerConnection : onDestroy ");
        Active = false;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Active = true;

        CallNumber = intent.getExtras().getString("EXTRA_NUMBER");

        PhoneState = intent.getExtras().getString("EXTRA_STATE");



        if(PhoneState.equals("IncomingCall"))

            FlowOnIncomingNumber();

        else

        if(PhoneState.equals("outGoingCall"))

            FlowOnOutgoingNumber();
        else

            System.out.println("Flow: ServerConnection : BadStatus!!! ");

    }



    private void FlowOnOutgoingNumber()
    {
        System.out.println("Flow: FlowOnOutgoingNumber : start ");

        HandlerOnOutgoingCall outGoingCallData = new HandlerOnOutgoingCall(getApplicationContext(),CallNumber);

        try
        {
            while(!outGoingCallData.getisRecNotification())
            {
                Thread.sleep(100);

            }


        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println("Flow: FlowOnOutgoingNumber : Done ");


    }

    private void FlowOnIncomingNumber()
    {
        System.out.println("Flow: FlowOnIncomingNumber : start ");


        HandlerOnIncomingCall IncomingCallData = new HandlerOnIncomingCall(getApplication(),CallNumber);

        try
        {
            while(Active)
            {
                Thread.sleep(100);

            }


        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println("Flow: FlowOnIncomingNumber : Done ");

        IncomingCallData.removeListener();


    }


}

