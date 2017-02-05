package com.nagajothy.smazee.emergency;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class Emergencyhome extends AppCompatActivity {
    Toolbar toolbar;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;
    String drawer;
    String Language , Purpose;

    TextView fire, emergency, police, pregnancy, women, accident , location;
    String locationdata;
    NetworkInfo networkInfo;
    ConnectivityManager check ;

    CoordinatorLayout coordinatorLayout;
  //  Smsclass smsclass;
    String SMS_STATUS;
    String number;
    String locationdatalatitude , locationdatalongitude , user_number , user_email ,user_name;
    String message_data_ambulance = "We need a Ambulance at ";
    String message_data_police = "Something Mischievous as happened we need Police at ";
    String message_data_fire = "Emergency Need a Fire Truck at";
    String message_data_accident = "We are in need of help ! ,Accident took Place at  ";
    String message_data_women = "Help me ! I need a Police at  ";
    private PopupWindow popupWindow;
    public final static String EXTRA_MESSAGE = "com.nagajothy.smazee.emergency.EmergencyhomeMESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencyhome);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        Intent get_user = getIntent();
        user_number = get_user.getStringExtra("Number");
        user_email = get_user.getStringExtra("Email");
        user_name = get_user.getStringExtra("Name");

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        String locationProvider = LocationManager.NETWORK_PROVIDER;
       // smsclass = new Smsclass();
       // String number = smsclass.SmsCheck(this);



        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);

        check = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = check.getActiveNetworkInfo();
        emergency = (TextView)findViewById(R.id.ambulance_text);
        fire = (TextView)findViewById(R.id.fire_text);
       // pregnancy = (TextView)findViewById(R.id.pregnancy_text);
        accident = (TextView)findViewById(R.id.accident_text);
        police = (TextView)findViewById(R.id.police_text);
        women = (TextView)findViewById(R.id.women_text);
        location = (TextView)findViewById(R.id.location_text);
        emergency = (TextView)findViewById(R.id.ambulance_text);

        Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
        Double lastlocationlatitude = lastKnownLocation.getLatitude();
        Double lastlocationlongitude = lastKnownLocation.getLongitude();
         locationdatalatitude = lastlocationlatitude.toString();
         locationdatalongitude = lastlocationlongitude.toString();
        // SmsManager smsManager = SmsManager.getDefault();
        // smsManager.sendTextMessage("phoneNo", null, "sms message", null, null);
        String locationdatatext = "You are at "+locationdatalatitude+"&"+locationdatalongitude;
        locationdata = locationdatalatitude+"&"+locationdatalongitude;
        Log.d(locationdata,"Location");
        location.setText(locationdatatext);
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        number = tm.getLine1Number();
        //location.setText(number);
        Log.d(number,"number");
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setTitle("Emergency App 108 ");
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.tamil:

                        getSupportActionBar().setTitle("அவசர விண்ணப்பம் 108");
                        emergency.setText("காவல்");
                        police.setText("மருத்துவமனையில்");

                        accident.setText("விபத்து");
                        women.setText("பெண்கள் பாதுகாப்பு");
                        fire.setText("தீ");
                        Language = "Tamil";


                        drawerLayout.closeDrawers();
                        break;
                    case R.id.english:
                        getSupportActionBar().setTitle("Emergency App 108 ");
                        emergency.setText("Emergency");
                        police.setText("Police");

                        accident.setText("Accident");
                        women.setText("Women Safety");
                        fire.setText("Fire");
                        Language = "English";
                        drawerLayout.closeDrawers();



                        break;
                    case R.id.telugu:
                        getSupportActionBar().setTitle("అత్యవసర అప్లికేషన్ 108");
                        emergency.setText("పోలీసు");
                        police.setText("హాస్పిటల్");

                        accident.setText("ప్రమాదంలో");
                        women.setText("మహిళల భద్రతకు");
                        fire.setText("అగ్ని");
                        Language = "Telugu";
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.malayalam:
                        getSupportActionBar().setTitle("അടിയന്തര അപ്ലിക്കേഷൻ 108");
                        emergency.setText("പോലീസ്");
                        police.setText("ആശുപത്രി");

                        accident.setText("അപകടം");
                        women.setText("സ്ത്രീകളുടെ സുരക്ഷയ്ക്കായി");
                        fire.setText("തീ");
                        Language = "Malayalam";
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.hindi:
                        getSupportActionBar().setTitle("इमरजेंसी आवेदन 108");
                        emergency.setText("पुलिस");
                        police.setText("अस्पताल");

                        accident.setText("दुर्घटना");
                        women.setText("महिलाओं की सुरक्षा");
                        fire.setText("आग");
                        Language = "Hindi";
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.kanada:
                        getSupportActionBar().setTitle("ತುರ್ತು ಅಪ್ಲಿಕೇಶನ್ 108");
                        emergency.setText("ಪೊಲೀಸ್");
                        police.setText("ಆಸ್ಪತ್ರೆ");

                        accident.setText("ಅಪಘಾತ");
                        women.setText("ಮಹಿಳಾ ಸುರಕ್ಷತೆ");
                        fire.setText("ಬೆಂಕಿ");
                        drawerLayout.closeDrawers();
                        Language = "kanada";
                        break;


                }
                return false;
            }
        });

    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    public void ambulance(View view)
    {


        if(networkInfo != null && networkInfo.isConnected())
        {
                Purpose = "Ambulance";
                Intent infointent = new Intent(this, Information.class);
                infointent.putExtra("language",Language);
                infointent.putExtra("latitude",locationdatalatitude);
                infointent.putExtra("longitude",locationdatalongitude);
                infointent.putExtra("name",user_name);
                infointent.putExtra("number",user_number);
                infointent.putExtra("purpose",Purpose);

                startActivity(infointent);

         //   LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
         //   View customview  = inflater.inflate(R.layout.ambulance_popup,null);
         //   popupWindow = new PopupWindow(customview, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
         //   if(Build.VERSION.SDK_INT>=21){
         //       popupWindow.setElevation(5.0f);
         //   }
         //   Button submit = (Button)findViewById(R.id.popup_submit);
         //   submit.setOnClickListener(new View.OnClickListener() {
         //       @Override
         //       public void onClick(View view) {
         //           Toast.makeText(Emergencyhome.this,"Will Win",Toast.LENGTH_LONG).show();
         //       }
         //   });
           // popupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER,0,0);




        }
        else {

           //Sending SMS

            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setData(Uri.parse("smsto:"));
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address", new String("8056160364"));
            smsIntent.putExtra("sms_body", message_data_ambulance + locationdata + "(Please Don't Edit it.)");
            try {
                startActivity(smsIntent);
                finish();
                SMS_STATUS = "Done ";
                //Log.i("Finished sending SMS...", "");

            } catch (android.content.ActivityNotFoundException ex) {
                SMS_STATUS = "SMS faild, please try again later.";
           }


        }
    }
    public void accident(View view)
    {


        if(networkInfo != null && networkInfo.isConnected())
        {
            Purpose = "Accident";
            Intent infointent = new Intent(this, Accident_Information.class);
            infointent.putExtra("language",Language);
            infointent.putExtra("latitude",locationdatalatitude);
            infointent.putExtra("longitude",locationdatalongitude);
            infointent.putExtra("name",user_name);
            infointent.putExtra("number",user_number);
            infointent.putExtra("purpose",Purpose);

            startActivity(infointent);


        }
        else {


            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setData(Uri.parse("smsto:"));
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address", new String("8056160364"));
            smsIntent.putExtra("sms_body", message_data_accident + locationdata + "(Please Don't Edit it.)");
            try {
                startActivity(smsIntent);
                finish();
                SMS_STATUS = "Done ";
                //Log.i("Finished sending SMS...", "");

            } catch (android.content.ActivityNotFoundException ex) {
                SMS_STATUS = "SMS faild, please try again later.";
            }


        }
    }
    public void fire(View view)
    {


        if(networkInfo != null && networkInfo.isConnected())
        {

            Purpose = "Fire";
            Intent infointent = new Intent(this, Fire_Information.class);
            infointent.putExtra("language",Language);
            infointent.putExtra("latitude",locationdatalatitude);
            infointent.putExtra("longitude",locationdatalongitude);
            infointent.putExtra("name",user_name);
            infointent.putExtra("number",user_number);
            infointent.putExtra("purpose",Purpose);

            startActivity(infointent);

        }
        else {


            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setData(Uri.parse("smsto:"));
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address", new String("8056160364"));
            smsIntent.putExtra("sms_body", message_data_fire + locationdata + "(Please Don't Edit it.)");
            try {
                startActivity(smsIntent);
                finish();
                SMS_STATUS = "Done ";
                //Log.i("Finished sending SMS...", "");

            } catch (android.content.ActivityNotFoundException ex) {
                SMS_STATUS = "SMS faild, please try again later.";
            }


        }
    }
    public void pregnancy(View view)
    {

        if(networkInfo != null && networkInfo.isConnected())
        {



        }
        else {


            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setData(Uri.parse("smsto:"));
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address", new String("8056160364"));
            smsIntent.putExtra("sms_body", message_data_ambulance + locationdata + "(Please Don't Edit it.)");
            try {
                startActivity(smsIntent);
                finish();
                SMS_STATUS = "Done ";
                //Log.i("Finished sending SMS...", "");

            } catch (android.content.ActivityNotFoundException ex) {
                SMS_STATUS = "SMS faild, please try again later.";
            }


        }
    }
    public void women(View view)
    {


        if(networkInfo != null && networkInfo.isConnected())
        {

            Purpose = "Women";
            Intent infointent = new Intent(this, WomenSafety.class);
            infointent.putExtra("language",Language);
            infointent.putExtra("latitude",locationdatalatitude);
            infointent.putExtra("longitude",locationdatalongitude);
            infointent.putExtra("name",user_name);
            infointent.putExtra("number",user_number);
            infointent.putExtra("purpose",Purpose);

            startActivity(infointent);

        }
        else {


            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setData(Uri.parse("smsto:"));
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address", new String("8056160364"));
            smsIntent.putExtra("sms_body", message_data_women + locationdata + "(Please Don't Edit it.)");
            try {
                startActivity(smsIntent);
                finish();
                SMS_STATUS = "Done ";
                //Log.i("Finished sending SMS...", "");

            } catch (android.content.ActivityNotFoundException ex) {
                SMS_STATUS = "SMS faild, please try again later.";
            }


        }
    }
    public void police(View view)
    {


        if(networkInfo != null && networkInfo.isConnected())
        {

            Purpose = "Police";
            Intent infointent = new Intent(this, Police_Information.class);
            infointent.putExtra("language",Language);
            infointent.putExtra("latitude",locationdatalatitude);
            infointent.putExtra("longitude",locationdatalongitude);
            infointent.putExtra("name",user_name);
            infointent.putExtra("number",user_number);
            infointent.putExtra("purpose",Purpose);

            startActivity(infointent);

        }
        else {


            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setData(Uri.parse("smsto:"));
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address", new String("8056160364"));
            smsIntent.putExtra("sms_body", message_data_police + locationdata + "(Please Don't Edit it.)");
            try {
                startActivity(smsIntent);
                finish();
                SMS_STATUS = "Done ";
                //Log.i("Finished sending SMS...", "");

            } catch (android.content.ActivityNotFoundException ex) {
                SMS_STATUS = "SMS faild, please try again later.";
            }


        }
    }

}
