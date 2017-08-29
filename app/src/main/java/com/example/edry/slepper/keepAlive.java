package com.example.edry.slepper;

import android.content.Context;
import android.media.AudioManager;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by edry on 16/08/2017.
 */

public class keepAlive extends RealTimeDatabase {

    private DatabaseReference Cref;
    private AudioManager MyVolume;
    private static final String KeepAliveStatusPath = "Status";

    protected static final String expireDatePath = "ExpireDate";
    protected static final String RingerLevelPath = "RingerLevel";
    protected static final String dndPath = "DND-Speaker";
    protected static final String TimeStempPath = "TimeStemp";
    protected static final String KeepAlivePath = "KeepAlive";



    private static final String status = "On";

    public keepAlive(Context contex) {

        super(contex , status );
        Cref = FirebaseDatabase.getInstance().getReference();
        MyVolume =  (AudioManager)contex.getSystemService(Context.AUDIO_SERVICE);

    }

    public void pushKeepAliveUpdateToServer()
    {
        setRealTimeDatabaseChanges(Cref.child(KeepAlivePath));
    }


    public String getPhoneNum(){return this.myPhoneNumber;};

    public void  updateRingerLevelEntry()
    {
        int Level = MyVolume.getStreamVolume(AudioManager.STREAM_RING);

        Cref.child(KeepAlivePath).child(myPhoneNumber).child(RingerLevelPath).setValue(Level);
    }


    public void  updatedndLevelEntry()
    {
        String DND = MyVolume.getRingerMode() == AudioManager.RINGER_MODE_SILENT ? "SILENT" : "NORMAL";

        Cref.child(KeepAlivePath).child(myPhoneNumber).child(dndPath).setValue(DND);
    }
    public void  updateTimeStempEntry()
    {
        Long  TimeStemp = System.currentTimeMillis();

        Cref.child(KeepAlivePath).child(myPhoneNumber).child(TimeStempPath).setValue(TimeStemp);
    }


}
