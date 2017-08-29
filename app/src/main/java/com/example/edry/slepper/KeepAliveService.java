package com.example.edry.slepper;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class KeepAliveService extends IntentService {

    private String phoneNumber;
    public KeepAliveService() {
        super("KeepAliveService");
    }

    protected void onHandleIntent(Intent intent) {

        System.out.println("Flow: KeepAliveService ");
        keepAlive keepAliveMassage = new keepAlive(getApplicationContext());
        keepAliveMassage.pushKeepAliveUpdateToServer();
        keepAliveMassage.updatedndLevelEntry();
        keepAliveMassage.updateRingerLevelEntry();
        keepAliveMassage.updateTimeStempEntry();

    }


}
