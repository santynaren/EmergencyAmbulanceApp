package com.nagajothy.smazee.emergency;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Created by SANTHOSH on 11/16/2016.
 */

public class Smsclass    {

    public  String   SmsCheck (Context context)
    {
        TelephonyManager tm = (TelephonyManager)context.getSystemService(TELEPHONY_SERVICE);
        String number = tm.getLine1Number();
        return  number;


    }


}
