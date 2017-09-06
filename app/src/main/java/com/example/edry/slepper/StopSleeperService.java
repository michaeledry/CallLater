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
public class StopSleeperService extends IntentService {



    public StopSleeperService() {
        super("StopSleeperService");
    }






    @Override
    protected void onHandleIntent(Intent intent) {

        System.out.println("Flow: StopSleeperService : sleep ");

        Intent newCallOutIntent = new Intent(getApplicationContext(), LunchSleeperService.class);

        stopService(newCallOutIntent);
    }


}
