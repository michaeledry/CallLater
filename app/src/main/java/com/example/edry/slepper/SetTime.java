package com.example.edry.slepper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.IntentFilter;
import android.icu.util.Calendar;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telecom.TelecomManager;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SetTime extends AppCompatActivity implements View.OnClickListener{

    private Button LogOut;
    private Button StartApp;
    private Button PendAll;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private TimePicker timePicker;

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    private MyReceiver Caller_Receiver = null;
    private IntentFilter filter = null;

    private static boolean onPeriod = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LogOut = (Button)findViewById(R.id.Blogoutuser);
        PendAll = (Button) findViewById(R.id.PendAll);
        StartApp = (Button)findViewById(R.id.BstartApp);

        LogOut.setOnClickListener(this);
        PendAll.setOnClickListener(this);
        StartApp.setOnClickListener(this);



    }


    protected void onDestroy() {
        super.onDestroy();

    }

    private void signOut() {
        mAuth.signOut();
    }


    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.BstartApp:

                onStartButton();

                break;

            case R.id.PendAll:

                onEndAllButton();

                break;

            case R.id.Blogoutuser:

                onSignOutButton();

                break;

            default:

                break;

        }
    }


    private void onSignOutButton()
    {
        mAuth = FirebaseAuth.getInstance();
        System.out.println(mAuth.getCurrentUser().toString());
        signOut();
        Intent backLoginPage = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(backLoginPage);
    }

    private void onStartButton()
    {

        alarmMgr = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        Intent intentI = new Intent(getApplicationContext(), KeepAliveReciever.class);
        alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intentI, 0);
        alarmMgr.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), 1000 * 60, alarmIntent);

        keepAlive keepAliveMassage = new keepAlive(getApplicationContext());
        keepAliveMassage.pushKeepAliveUpdateToServer();

        if(Caller_Receiver == null) {

            System.out.println("Flow: SetTime : init Caller_Receiver  " );

            Caller_Receiver = new MyReceiver();
            if(filter ==null)
            {
                filter = new IntentFilter("android.intent.action.PHONE_STATE" );
                filter.addAction("android.intent.action.NEW_OUTGOING_CALL");

                System.out.println("Flow: SetTime : init filter  " );

            }

            registerReceiver(Caller_Receiver, filter);

        }



        MyPhoneState TakeActionOnCall = new MyPhoneState();
        TakeActionOnCall.onCallStateChanged(getApplicationContext(),2,null);

        DisconnectionActiveUserMassage errorLogInFBServer = new DisconnectionActiveUserMassage(getApplicationContext(),"Available");


         goHomeScreen();
    }

    private void onEndAllButton()
    {
        System.out.println("Flow: SetTime : start  " );

        DisconnectionMassage jobDone = new DisconnectionMassage(getApplicationContext());
        OffUserMassge OffStatusmassage = new OffUserMassge(getApplicationContext());
        MyPhoneState TakeActionOnCall = new MyPhoneState();
        TakeActionOnCall.onCallStateChanged(getApplicationContext(),3,null);

        try {
            alarmMgr.cancel(alarmIntent);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Flow: SetTime : crashed alarmMgr " );

            DisconnectionActiveUserMassage errorLogInFBServer = new DisconnectionActiveUserMassage(getApplicationContext(),"Crashed02");
        }

        try
        {
            System.out.println("Flow: SetTime : unregisterReceiver  " );

            unregisterReceiver(Caller_Receiver);

        }
        catch (Exception e)
        {
            e.printStackTrace();

            System.out.println("Flow: SetTime : crashed  " );

            DisconnectionActiveUserMassage errorLogInFBServer = new DisconnectionActiveUserMassage(getApplicationContext(),"Crashed01");
        }




       // goHomeScreen();
       // onDestroy();

        System.out.println("Flow: SetTime : before finish  " );


        Intent CloseApp = new Intent(getApplicationContext(),MainActivity.class);
        CloseApp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        CloseApp.putExtra("KILL","TRUE");
        startActivity(CloseApp);




    }

    private void goHomeScreen()
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }




}
