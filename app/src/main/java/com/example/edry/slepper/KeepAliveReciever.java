package com.example.edry.slepper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class KeepAliveReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intentI = new Intent(context, KeepAliveService.class);

        context.startService(intentI);
    }
}
