package com.example.edry.slepper;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class appTermination extends IntentService {

    public appTermination() {
        super("appTermination");
    }


    protected void onHandleIntent(Intent intent) {

        //Toast.makeText(getApplicationContext(),"Job Done",Toast.LENGTH_LONG).show();
        //System.out.println("Flow: appTermination ");
        //MyReceiver rec = (MyReceiver) intent.getSerializableExtra("BReciever");
        //unregisterReceiver(rec);
    }

}
