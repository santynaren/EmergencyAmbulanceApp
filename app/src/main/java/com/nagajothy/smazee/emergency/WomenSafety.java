package com.nagajothy.smazee.emergency;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class WomenSafety extends AppCompatActivity {


    String Language , longi;
    String latitude , longitude ,lat;
    EditText phone_number_text , landmark_text , reason_text;
    String phonenumber , landmark , temp_number ;

    String URL_REGISTER = "http://192.168.1.7/emergencyinsert.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_women_safety);

        phone_number_text =  (EditText)findViewById(R.id.phone_number) ;
        landmark_text = (EditText)findViewById(R.id.landmark);
        reason_text = (EditText)findViewById(R.id.reason);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Fill in the Proper Details");

        Intent intent = getIntent();
        latitude = intent.getStringExtra("latitude");
        longitude = intent.getStringExtra("longitude");
        Language = intent.getStringExtra("language");
        temp_number = intent.getStringExtra("number");
        phone_number_text.setText(temp_number);

        //a function to trigger the auto sms function to the important Contacts
    }

    public void pass(View view)
    {


        phonenumber = phone_number_text.getText().toString();
        landmark = landmark_text.getText().toString();

        if(landmark.equals(""))
        {
            landmark="null";
        }


        background con = new background();
        con.execute(phonenumber,landmark,latitude,longitude);

    }

    class background extends AsyncTask<String,Void,String>
    {
        String task_req;

        @Override
        protected void onPreExecute() {
            task_req ="http://192.168.43.32/womeninsert.php";
        }

        @Override
        protected String doInBackground(String... args) {
            String p , l ,la ,lo,r,c;
            p = args[0];
            l = args[1];
            la = args[2];
            lo = args[3];

            try {
                URL url = new URL(task_req);
                HttpURLConnection addconnection = (HttpURLConnection) url.openConnection();
                addconnection.setRequestMethod("POST");
                addconnection.setDoOutput(true);
                OutputStream outputStream = addconnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data_to_be_added = URLEncoder.encode("phonenumber","UTF-8")+"="+URLEncoder.encode(p,"UTF-8")+"&"+
                        URLEncoder.encode("landmark","UTF-8")+"="+URLEncoder.encode(l,"UTF-8")+"&"+
                        URLEncoder.encode("latitude","UTF-8")+"="+URLEncoder.encode(la, "UTF-8")+"&"+
                        URLEncoder.encode("longitude","UTF-8")+"="+URLEncoder.encode(lo, "UTF-8");
                bufferedWriter.write(data_to_be_added);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = addconnection.getInputStream();
                inputStream.close();
                addconnection.disconnect();
                Log.d("number",p);
                Log.d("landmark",l);
                Log.d("latitude",la);


                return "Police Called !!";



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            Intent intent = new Intent(WomenSafety.this,Police_final.class);
            startActivity(intent);
            finish();
        }
    }



}
