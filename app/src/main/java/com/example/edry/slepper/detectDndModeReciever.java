package com.example.edry.slepper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

public class detectDndModeReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        AudioManager MyVolume =  (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

        System.out.println("Flow: detectDndModeReciever : onReceive " + MyVolume.getRingerMode() + SetTime.onPeriod) ;

        if(SetTime.onPeriod == false) {

            switch (MyVolume.getRingerMode()) {
                case AudioManager.RINGER_MODE_SILENT:

                    Intent startSleep = new Intent(context, SetTime.class);

                    startSleep.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    context.startActivity(startSleep);

                    break;

                case AudioManager.RINGER_MODE_NORMAL:

                    break;

                case AudioManager.RINGER_MODE_VIBRATE:

                    break;


                default:

                    break;


            }

        }
    }
}
