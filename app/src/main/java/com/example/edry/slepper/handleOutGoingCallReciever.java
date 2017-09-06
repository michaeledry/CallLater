package com.example.edry.slepper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class handleOutGoingCallReciever extends BroadcastReceiver {


    private String adiitionalPhoneNumber;
    private FirebaseUser user;
    private FirebaseAuth mAuth;

    public void onReceive(Context context, Intent intent) {


        mAuth = FirebaseAuth.getInstance();

        user = mAuth.getCurrentUser();

        System.out.println("Flow: handleOutGoingCallReciever : onReceive " + user.getPhoneNumber()) ;

        if (user != null) {

            System.out.println("Flow: handleOutGoingCallReciever : user is not null") ;

            adiitionalPhoneNumber = intent.getExtras().getString(Intent.EXTRA_PHONE_NUMBER);

            Intent newCallOutIntent = new Intent(context, ServerConnection.class);

            newCallOutIntent.putExtra("EXTRA_STATE", "outGoingCall");

            newCallOutIntent.putExtra("EXTRA_NUMBER", adiitionalPhoneNumber);

            context.startService(newCallOutIntent);
        }
        else
            System.out.println("Flow: handleOutGoingCallReciever : user is null") ;


    }

}
