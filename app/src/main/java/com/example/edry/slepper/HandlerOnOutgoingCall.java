package com.example.edry.slepper;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by edry on 21/08/2017.
 */

public class HandlerOnOutgoingCall extends OnCallDatabase{

    private static final String ansToCompare = "On";

    public HandlerOnOutgoingCall(Context contex, String PhoneNum) {

        super(contex, PhoneNum);

        System.out.println("Flow: HandlerOnOutgoingCall : constructor ");

        CheckDataBaseState();
    }


    protected boolean handleServerResponse(String Response) {

        ActionOnServerResponse ActionHandler = new ActionOnServerResponse(myContex,PhoneNum);

        int ans = CaseResponse(Response, ansToCompare);

        System.out.println("Flow: HandlerOnOutgoingCall : handleServerResponse " + ans);


        switch(ans)
        {
            case 0:                                         //null
                //ActionHandler.SendSms(smsMassageOnNull);
                break;

            case 1:                                        //On
                ActionHandler.openPopUpForUI();
                break;

            case 2:                                       //Off

                break;

            default:

                break;


        }


        return true ;

    }

}
