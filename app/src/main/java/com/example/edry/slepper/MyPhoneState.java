
package com.example.edry.slepper;

/**
 * Created by edry on 07/08/2017.
 */

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Build;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.net.Uri;

public class MyPhoneState extends PhoneStateListener{

    public enum Cases {
        INCOMING_CALL , IDLE
    }
    //hghghjghjghjghj

    public void onCallStateChanged(Context context, int state, String number)
    {

        AudioManager MyVolume =  (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

        switch(state)
        {
            case 0:
                try {

                    Intent ringerServic = new Intent(context,RingtonePlayerService.class);

                    context.stopService(ringerServic);

                    MyVolume.setStreamVolume(AudioManager.STREAM_NOTIFICATION, 0,0);
                    //MyVolume.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);


                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("broadcast audioState defual");
                }
                finally {
                    Intent intentI = new Intent(context, KeepAliveService.class);

                    context.startService(intentI);
                }
                break;

            case 1:
                try {

                   //MyVolume.setStreamVolume(AudioManager.STREAM_RING, MyVolume.getStreamMaxVolume(AudioManager.STREAM_RING), 0);
                    //MyVolume.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

                    MyVolume.setStreamVolume(AudioManager.STREAM_NOTIFICATION,MyVolume.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION), 0);

                   Intent ringerServic = new Intent(context,RingtonePlayerService.class);

                   context.startService(ringerServic);


                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Flow : no change at ringer");
                    Toast.makeText(context,number,Toast.LENGTH_LONG).show();

                }
                finally {
                    Intent intentI = new Intent(context, KeepAliveService.class);

                    context.startService(intentI);
                }




                break;
            case 4:

                try {
                    //MyVolume.setStreamVolume(AudioManager.STREAM_RING, 0, 0);

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    Intent intentI = new Intent(context, KeepAliveService.class);

                    context.startService(intentI);
                }

                break;
            case 3:

                try {
                    MyVolume.setStreamVolume(AudioManager.STREAM_RING, MyVolume.getStreamMaxVolume(AudioManager.STREAM_RING), 0);
                }
                catch (Exception e) {
                    e.printStackTrace();


                }


                break;
            default:
                System.out.println("defual");
                break;
        }

    }



}
