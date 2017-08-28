package com.example.edry.slepper;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by edry on 21/08/2017.
 */

public class HandlerOnIncomingCall  extends OnCallDatabase{

    private static final String confirmationStatus = "On";


    ActionOnServerResponse ActionHandler;
    DatabaseReference refStatus;

    public HandlerOnIncomingCall(Context contex, String PhoneNum) {

        super(contex, PhoneNum);

        CheckDataBaseState();

        System.out.println("Flow: HandlerOnOutgoingCall : constructor ");

    }


    protected boolean handleServerResponse(String Response) {

        ActionHandler = new ActionOnServerResponse(myContex,PhoneNum);

        int ans = CaseResponse(Response, confirmationStatus);

        switch(ans)
        {
            case 0: // Application does not existed in caller phone

                //ActionHandler.SendSms(smsMassageOnNull);

                break;

            case 1: //  Application exists in caller phone

                 ActionHandler.ListenForCallStatus(Cref.child(ActiveUsersPath).child(this.myPhoneNumber),Cref.child(ActiveUsersPath).child(this.myPhoneNumber).child("Emergency"));

                break;

            case 2:

                break;

            default:

                break;


        }


        return (ans == 1) ? false : false;

    }

    public void removeListener()
    {
        ActionHandler.removeListener();
    }

    public String getDatabaseStatusFeild()
    {
        if(ActionHandler != null)

            return ActionHandler.isjobDone();

        else

            return "Mute";
    }
}
