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


    private Button StartApp;
    private Button PendAll;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private TimePicker timePicker;

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    private MyReceiver Caller_Receiver = null;
    private IntentFilter filter = null;

    public static boolean onPeriod = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PendAll = (Button) findViewById(R.id.PendAll);
        StartApp = (Button)findViewById(R.id.BstartApp);


        PendAll.setOnClickListener(this);
        StartApp.setOnClickListener(this);



    }


    protected void onDestroy() {
        super.onDestroy();
        System.out.println("SetTime.onDestroy :  " );

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

            default:

                break;

        }
    }


    private void onStartButton()
    {

        Intent newCallOutIntent = new Intent(getApplicationContext(), LunchSleeperService.class);

        startService(newCallOutIntent);

        //this.onPeriod = true;

        //Intent CloseApp = new Intent(getApplicationContext(),MainActivity.class);
        //CloseApp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //CloseApp.putExtra("KILL","TRUE");
        //startActivity(CloseApp);

        goHomeScreen();

    }

    private void onEndAllButton()
    {

        Intent newCallOutIntent = new Intent(getApplicationContext(), LunchSleeperService.class);

        stopService(newCallOutIntent);

        //this.onPeriod = false;

        goHomeScreen();
       // onDestroy();



    }

    private void goHomeScreen()
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }




}
