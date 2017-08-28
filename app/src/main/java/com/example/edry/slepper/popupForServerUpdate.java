package com.example.edry.slepper;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class popupForServerUpdate extends AppCompatActivity {

    private Button Buttom1;
    private Button buttonDismiss;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_for_server_update);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        getWindow().setLayout((int)  (dm.widthPixels*.8),(int)(dm.heightPixels*.6));

        phoneNumber = getIntent().getStringExtra("phoneNum");
        Toast.makeText(getApplicationContext(),"outgoing call to "+ phoneNumber,Toast.LENGTH_LONG).show();
        Buttom1 = (Button) findViewById(R.id.ButtonInterpt);
        Buttom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignalPeerMassage msg = new SignalPeerMassage(getApplicationContext(), phoneNumber);
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


    }

}
