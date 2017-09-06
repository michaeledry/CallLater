package com.example.edry.slepper;

import android.*;
import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button StartButton;
    private AudioManager Manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String Extra = getIntent().getStringExtra("KILL");
        if(Extra != null && Extra.equals("TRUE"))
        {
            finish();
            //onDestroy();
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Manager = (AudioManager) getApplicationContext().getSystemService(getApplicationContext().AUDIO_SERVICE);

        this.StartButton= (Button) findViewById ( R.id.StartButton );

        this.StartButton.setOnClickListener(
                new Button.OnClickListener()
                {
                    public void onClick(View w)
                    {
                        Intent TimeSet =  new Intent(getApplicationContext() , phoneLoginActivity.class);
                        startActivity(TimeSet);
                    }
                }


        );






        NotificationManager notificationManager =
                (NotificationManager) getApplicationContext().getSystemService(getApplicationContext().NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
                && !notificationManager.isNotificationPolicyAccessGranted()) {

            Intent intent = new Intent(
                    android.provider.Settings
                            .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);

            startActivity(intent);

        }
        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.SEND_SMS},1);


        int permissionCheckForPhoneState = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheckForPhoneState != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_PHONE_STATE}, 1);
        }


        int permissionCheckForOutGoingCall = ContextCompat.checkSelfPermission(this, Manifest.permission.PROCESS_OUTGOING_CALLS);

       // if (permissionCheckForOutGoingCall != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.PROCESS_OUTGOING_CALLS}, 1);
        //}




    }
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //TODO
                }
                break;

            default:
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("Flow: MainActivity : onStop() ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("Flow: MainActivity : onDestroy() ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("Flow: MainActivity : onPause() ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("Flow: MainActivity : onStart() ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Flow: MainActivity : onResume() ");

    }

}
