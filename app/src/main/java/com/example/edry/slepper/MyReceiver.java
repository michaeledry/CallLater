package com.example.edry.slepper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {


    public MyReceiver() {
        super();
    }

    private String PhoneState;

    private String adiitionalPhoneNumber;


    public void onReceive(Context context, Intent intent) {

        PhoneState = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);

        Intent newIn = new Intent(context,MainActivity.class);

        context.startActivity(newIn);



        int curState = getState();

        switch (curState) {

            case TelephonyManager.CALL_STATE_IDLE:

                handleOnIdleState(context, intent);

                break;

            case TelephonyManager.CALL_STATE_RINGING:

                handleOnIncomingCall(context, intent);

                break;

            case TelephonyManager.CALL_STATE_OFFHOOK:

                handleHookUpState( context,  intent);

                break;

            default:


                break;


        }

        System.out.println("Flow: MyReceiver : case " + curState);


        System.out.println("Flow: MyReceiver : onReceive  " + intent.getAction() + adiitionalPhoneNumber);

        }




    public void onDestroy() {
        System.out.println("Flow: MyReceiver : Destroy ");

    }


    private void handleOnIncomingCall(Context context, Intent intent)

    {

        adiitionalPhoneNumber = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
        System.out.println("Flow: MyReceiver : CALL_STATE_Ring  " + adiitionalPhoneNumber);
            Intent newCallInIntent = new Intent(context, ServerConnection.class);
            newCallInIntent.putExtra("EXTRA_STATE","IncomingCall");
            newCallInIntent.putExtra("EXTRA_NUMBER",adiitionalPhoneNumber);
            context.startService(newCallInIntent);


    }

    private void handleOnIdleState(Context context, Intent intent)
    {

        Intent newIntent = new Intent(context, ServerConnection.class);
        context.stopService(newIntent);
        MyPhoneState TakeActionOnCall = new MyPhoneState();
        DisconnectionActiveUserMassage EndSession = new DisconnectionActiveUserMassage("Available");
        TakeActionOnCall.onCallStateChanged(context,0,null);

    }


    private void handleHookUpState(Context context, Intent intent)
    {

        //Intent ringerServic = new Intent(context,RingtonePlayerService.class);


        //context.stopService(ringerServic);
    }


    private int getState( )
    {

        int state = -1;
        if(PhoneState.equals("IDLE")){
            state = TelephonyManager.CALL_STATE_IDLE;
        }
        else if(PhoneState.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
            state = TelephonyManager.CALL_STATE_OFFHOOK;
        }
        else if(PhoneState.equals("RINGING")){
            state = TelephonyManager.CALL_STATE_RINGING;
        }
        return state;
    };


}


