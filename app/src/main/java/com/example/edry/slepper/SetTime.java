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


public class SetTime extends AppCompatActivity {

    private Button LogOut;
    private Button StartApp;
    private Button PendAll;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private TimePicker timePicker;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    private Intent sleepLimiter;
    private String myPhoneNum;

    public MyReceiver Caller_Receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myPhoneNum = (String) getIntent().getStringExtra("phoneNumber");
        Toast.makeText(getApplicationContext(),myPhoneNum,Toast.LENGTH_LONG).show();

        LogOut = (Button)findViewById(R.id.button2);
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                System.out.println(mAuth.getCurrentUser().toString());
                signOut();
                Intent backLoginPage = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(backLoginPage);

            }
        });

        PendAll = (Button) findViewById(R.id.PendAll);
        PendAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DisconnectionMassage jobDone = new DisconnectionMassage(getApplicationContext());
                OffUserMassge OffStatusmassage = new OffUserMassge(getApplicationContext());

                try
                {


                    Caller_Receiver = new MyReceiver();
                    IntentFilter filter = new IntentFilter();
                    registerReceiver(Caller_Receiver, filter);
                    Thread.sleep(1000);


                    unregisterReceiver(Caller_Receiver);

                }
                catch (Exception e)
                {
                 e.printStackTrace();
                    DisconnectionActiveUserMassage errorLogInFBServer = new DisconnectionActiveUserMassage(getApplicationContext(),"Crashed01");
                }


                try
                {
                    AudioManager MyVolume = (AudioManager)getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                    MyVolume.setStreamVolume(AudioManager.STREAM_RING, MyVolume.getStreamMaxVolume(AudioManager.STREAM_RING), 0);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Erro while modifying ringer", Toast.LENGTH_LONG).show();
                }

                goHomeScreen();
            }
        });


        StartApp = (Button)findViewById(R.id.BstartApp);
        StartApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newIntent = new Intent(getApplicationContext(),KeepAliveService.class);
                newIntent.putExtra("phoneNumber", myPhoneNum);
                getApplicationContext().startService(newIntent);
                DisconnectionActiveUserMassage EndSession = new DisconnectionActiveUserMassage(getApplicationContext(),"Available");



                //alarmMgr.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() +
                  //       1000, alarmIntent);


                Caller_Receiver = new MyReceiver();
                IntentFilter filter = new IntentFilter();
                registerReceiver(Caller_Receiver, filter);

                MyPhoneState TakeActionOnCall = new MyPhoneState();
                TakeActionOnCall.onCallStateChanged(getApplicationContext(),0,null);


//                goHomeScreen();

            }
        });

        timePicker = (TimePicker)findViewById(R.id.timePicker);
        alarmMgr = (AlarmManager)getApplicationContext().getSystemService(getApplicationContext().ALARM_SERVICE);
        sleepLimiter = new Intent(getApplicationContext(),appTermination.class);
        alarmIntent = PendingIntent.getBroadcast(getApplicationContext(),0,sleepLimiter,0);


    }

    @Override
    protected void onStop() {

        super.onStop();
        System.out.println("Flow: SetTime : onDestroy  " );

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void signOut() {
        mAuth.signOut();
    }

    private void goHomeScreen()
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
