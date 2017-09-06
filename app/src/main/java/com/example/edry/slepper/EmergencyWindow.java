package com.example.edry.slepper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by edry on 06/09/2017.
 */

public class EmergencyWindow extends PopUpWindow  implements View.OnClickListener{

    Button DismissButton;

    Button ApproveButton;

    String phoneNumber;


    public EmergencyWindow(Context myContext, String phoneNumber) {

        super(myContext);

        this.phoneNumber = phoneNumber;
    }

    @Override
    public void setInflator() {

        this.floatyView = ((LayoutInflater) this.myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.emergency_view, interceptorLayout);

    }

    @Override
    public void setScreenContent() {

        DismissButton = (Button) floatyView.findViewById(R.id.DismissButton);

        ApproveButton =  (Button) floatyView.findViewById(R.id.confirmButton);

        DismissButton.setOnClickListener(this);

        ApproveButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.DismissButton:

                break;

            case R.id.confirmButton:

                    SignalPeerMassage msg = new SignalPeerMassage(myContext, phoneNumber);

                break;

            default:

                break;
        }

        removeView();
    }
}
