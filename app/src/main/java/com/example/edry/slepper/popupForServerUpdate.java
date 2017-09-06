package com.example.edry.slepper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;


public class popupForServerUpdate extends AppCompatActivity {

    private Button Buttom1;
    private Button buttonDismiss;
    private String phoneNumber;

    AlertDialog.Builder builder1;
    AlertDialog alert11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(android.R.style.Theme_Dialog);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.DECOR_CAPTION_SHADE_AUTO);

        setContentView(R.layout.activity_popup_for_server_update);
       DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        getWindow().setLayout((int)  (dm.widthPixels*.2),(int)(dm.heightPixels*.1));

        phoneNumber = getIntent().getStringExtra("phoneNum");
        Toast.makeText(getApplicationContext(),"outgoing call to "+ phoneNumber,Toast.LENGTH_LONG).show();
        Buttom1 = (Button) findViewById(R.id.ButtonInterpt);
        Buttom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignalPeerMassage msg = new SignalPeerMassage( phoneNumber);
                finish();
            }
        });



        buttonDismiss = (Button) findViewById(R.id.buttonDismiss);
        buttonDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        builder1 = new AlertDialog.Builder(popupForServerUpdate.this);
        builder1.setMessage("Emergency dialog.");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SignalPeerMassage msg = new SignalPeerMassage( phoneNumber);

                        dialog.cancel();

                        onDestroy();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                        onDestroy();
                    }
                });

        alert11 = builder1.create();
        alert11.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.finish();
        goHomeScreen();
    }

    private void goHomeScreen()
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
