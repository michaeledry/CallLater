package com.example.edry.slepper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by edry on 06/09/2017.
 */

public class startSleeperWindow extends PopUpWindow {

    TimePicker timePicker;


    public startSleeperWindow(Context myContext) {
        super(myContext);
    }

    @Override


    public void setInflator() {
        this.floatyView = ((LayoutInflater) this.myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.floating_view, interceptorLayout);
    }


    public void setScreenContent() {
        Button confirm = (Button)  floatyView.findViewById(R.id.confirmButton);

        Button ExitButton  = (Button) floatyView.findViewById(R.id.ExitButton);

        timePicker = (TimePicker) floatyView.findViewById(R.id.simpleTimePicker);

        timePicker.setIs24HourView(true);


        confirm.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {



                Calendar calNow = Calendar.getInstance();
                Calendar calSet = (Calendar) calNow.clone();

                calSet.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                calSet.set(Calendar.MINUTE, timePicker.getMinute());
                calSet.set(Calendar.SECOND, 0);
                calSet.set(Calendar.MILLISECOND, 0);

                if(calSet.compareTo(calNow) <= 0){
                    //Today Set time passed, count to tomorrow
                    calSet.add(Calendar.DATE, 1);
                }

                Intent newCallOutIntent = new Intent(myContext, LunchSleeperService.class);

                newCallOutIntent.putExtra("PERIOD",calSet.getTimeInMillis());




                PendingIntent pi = PendingIntent.getService(myContext, 0,
                        new Intent(myContext, StopSleeperService.class), 0);
                AlarmManager am = (AlarmManager) myContext.getSystemService(Context.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, calSet.getTimeInMillis(), pi);


                Toast.makeText(myContext,"Sleep till : "+timePicker.getHour() +":" + timePicker.getMinute(),Toast.LENGTH_LONG);

                myContext.startService(newCallOutIntent);

                removeView();

            }
        });

        ExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView();
            }
        });
    }
}
