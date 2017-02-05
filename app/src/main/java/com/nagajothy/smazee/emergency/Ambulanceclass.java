package com.nagajothy.smazee.emergency;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by SANTHOSH on 11/16/2016.
 */

public class Ambulanceclass extends Emergencyhome {
    Smsclass smstrigger;
    String Status;




  public String Ambulancecall(String locationdata)
  {
      String Message_data = "We need a Ambulance at ";

      ConnectivityManager check = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo networkInfo = check.getActiveNetworkInfo();
      if(networkInfo != null && networkInfo.isConnected())
      {
        //Status =  smstrigger.SmsCheck(locationdata,Message_data);

      }
      else
      {

      }
      return Status;
  }

}
