package com.example.edry.slepper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;

public class LunchSleeperService extends Service {


    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    private MyReceiver Caller_Receiver = null;
    private IntentFilter filter = null;



    public LunchSleeperService() {
    }


    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId)
    {
        FirebaseDatabase.getInstance().setLogLevel(Logger.Level.DEBUG);

        System.out.println("Flow: LunchSleeperService : sleep till " + (long)intent.getLongExtra("PERIOD",0)) ;



        alarmMgr = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);

        Intent intentI = new Intent(getApplicationContext(), KeepAliveReciever.class);

        alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intentI, 0);

        alarmMgr.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), 1000 * 60, alarmIntent);



        keepAlive keepAliveMassage = new keepAlive(getApplicationContext());

        keepAliveMassage.pushKeepAliveUpdateToServer();

        Caller_Receiver = new MyReceiver();

        filter = new IntentFilter("android.intent.action.PHONE_STATE" );

        registerReceiver(Caller_Receiver, filter);

        DisconnectionActiveUserMassage errorLogInFBServer = new DisconnectionActiveUserMassage("Available");

        return START_NOT_STICKY;

    }

    public void onDestroy()
    {
        System.out.println("Flow: LunchSleeperService : onDestroy ") ;

        DisconnectionMassage jobDone = new DisconnectionMassage();

        OffUserMassge OffStatusmassage = new OffUserMassge();

        MyPhoneState TakeActionOnCall = new MyPhoneState();

        TakeActionOnCall.onCallStateChanged(getApplicationContext(),3,null);

        try {
            alarmMgr.cancel(alarmIntent);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Flow: SetTime : crashed alarmMgr " );

            DisconnectionActiveUserMassage errorLogInFBServer = new DisconnectionActiveUserMassage("Crashed02");
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

            DisconnectionActiveUserMassage errorLogInFBServer = new DisconnectionActiveUserMassage("Crashed01");
        }


    }

}
