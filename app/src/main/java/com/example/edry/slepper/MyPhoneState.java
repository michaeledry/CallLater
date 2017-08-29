
package com.example.edry.slepper;

/**
 * Created by edry on 07/08/2017.
 */

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
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
                    //MyVolume.setStreamVolume(AudioManager.STREAM_RING, 0, 0);
                    MyVolume.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    Thread.sleep(2000);
                    MyVolume.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("broadcast audioState defual");
                }
                break;

            case 1:
                try {

                    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener(){
                        public void onAudioFocusChange(int focusChange) {

                        }
                    };
                        MyVolume.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                        Thread.sleep(2000);
                    MyVolume.requestAudioFocus(afChangeListener, AudioManager.STREAM_RING, // Music streaming
                            AudioManager.AUDIOFOCUS_GAIN);
                    MyVolume.adjustVolume(AudioManager.ADJUST_UNMUTE,AudioManager.FLAG_PLAY_SOUND );
                    Thread.sleep(2000);


                    MyVolume.setStreamVolume(AudioManager.STREAM_RING, MyVolume.getStreamMaxVolume(AudioManager.STREAM_RING), 0);
                    System.out.println("Flow : onCallStateChanged : Case 1 set to Maxvolume , "+ MyVolume.getStreamVolume(AudioManager.STREAM_RING));

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Flow : no change at ringer");
                    Toast.makeText(context,number,Toast.LENGTH_LONG).show();

                }
                finally {


                }

                break;
            default:
                System.out.println("defual");
                break;
        }

    }



}
