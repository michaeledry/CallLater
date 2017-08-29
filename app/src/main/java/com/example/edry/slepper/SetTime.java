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

    public MyReceiver Caller_Receiver;

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

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

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
        System.out.println("Flow: SetTime : try to scedule keepalive process  " );

        alarmMgr = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        Intent intentI = new Intent(getApplicationContext(), KeepAliveReciever.class);
        alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intentI, 0);
        alarmMgr.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), 1000 * 60, alarmIntent);

        Caller_Receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        registerReceiver(Caller_Receiver, filter);

        MyPhoneState TakeActionOnCall = new MyPhoneState();
        TakeActionOnCall.onCallStateChanged(getApplicationContext(),0,null);

        DisconnectionActiveUserMassage errorLogInFBServer = new DisconnectionActiveUserMassage(getApplicationContext(),"Available");


         goHomeScreen();
    }

    private void onEndAllButton()
    {
        DisconnectionMassage jobDone = new DisconnectionMassage(getApplicationContext());
        OffUserMassge OffStatusmassage = new OffUserMassge(getApplicationContext());
        MyPhoneState TakeActionOnCall = new MyPhoneState();
        TakeActionOnCall.onCallStateChanged(getApplicationContext(),3,null);
        alarmMgr.cancel(alarmIntent);

        try
        {
            unregisterReceiver(Caller_Receiver);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            DisconnectionActiveUserMassage errorLogInFBServer = new DisconnectionActiveUserMassage(getApplicationContext(),"Crashed01");
        }




        goHomeScreen();
    }

    private void goHomeScreen()
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
