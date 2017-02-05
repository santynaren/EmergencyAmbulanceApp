package com.nagajothy.smazee.emergency;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Created by SANTHOSH on 11/17/2016.
 */

public class EmergencyWidget extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for(int i=0; i<appWidgetIds.length; i++){
            int currentWidgetId = appWidgetIds[i];
           // String url = "http://www.tutorialspoint.com";

            //Intent intent = new Intent(Intent.ACTION_VIEW);
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //intent.setData(Uri.parse(url));

//            PendingIntent pending = PendingIntent.getActivity(context, 0,intent, 0);
  //          RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.emergencywidgetlayout);
//
  //          views.setOnClickPendingIntent(R.id.amb, pending);
//            appWidgetManager.updateAppWidget(currentWidgetId,views);


            Toast.makeText(context, "widget added", Toast.LENGTH_SHORT).show();




        }
    }
}
