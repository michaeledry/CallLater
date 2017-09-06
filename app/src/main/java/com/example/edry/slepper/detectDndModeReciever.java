package com.example.edry.slepper;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.icu.util.Calendar;
import android.media.AudioManager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.support.v7.app.AppCompatActivity;

import static android.content.Context.ALARM_SERVICE;

public class detectDndModeReciever extends BroadcastReceiver {




    public void onReceive(final Context context, Intent intent) {

        System.out.println("Flow: detectDndModeReciever : sleep till " ) ;


        AudioManager MyVolume =  (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

        if(SetTime.onPeriod == false) {

            switch (MyVolume.getRingerMode()) {

                case AudioManager.RINGER_MODE_SILENT:

                    if(! isMyServiceRunning(LunchSleeperService.class,context)) {

                        startSleeperWindow Slper = new startSleeperWindow(context);
                    }

                    break;

                case AudioManager.RINGER_MODE_NORMAL:

                    if(isMyServiceRunning(LunchSleeperService.class,context)) {

                        StopSleeperWindow Slper = new StopSleeperWindow(context);
                    }

                    break;

                case AudioManager.RINGER_MODE_VIBRATE:

                    break;


                default:

                    break;


            }

        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass , Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


}
