package com.nagajothy.smazee.emergency;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Accident_Information extends AppCompatActivity {
    String Language , longi;
    String latitude , longitude ,lat;
    EditText phone_number_text , landmark_text , reason_text , victims_text;
    String phonenumber , landmark , victims , reason , temp_number ;

    String URL_REGISTER = "http://192.168.43.32/emergencyinsert.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accident__information);

        phone_number_text =  (EditText)findViewById(R.id.phone_number) ;
        landmark_text = (EditText)findViewById(R.id.landmark);
        reason_text = (EditText)findViewById(R.id.reason);
        victims_text = (EditText) findViewById(R.id.victims);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Fill in the Proper Details");

        Intent intent = getIntent();
        latitude = intent.getStringExtra("latitude");
        longitude = intent.getStringExtra("longitude");
        Language = intent.getStringExtra("language");
        temp_number = intent.getStringExtra("number");
        phone_number_text.setText(temp_number);



    }
    public void accidentpass(View view)
    {

        victims = victims_text.getText().toString();
        phonenumber = phone_number_text.getText().toString();
        landmark = landmark_text.getText().toString();
        reason = reason_text.getText().toString();
        if(reason.equals(""))
        {
            reason="null";
        }



        // background con = new background();
        // con.execute(phonenumber,landmark,latitude,longitude);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // JSONArray jsonArray  = jsonObject.getJSONArray("First");
                    //JSONObject jsonObj = new JSONObject(response);
                    // JSONArray jsonArray = new JSONArray(response);
                    // JSONObject jsonObject = jsonArray.getJSONObject(0);
                    //JSONArray contacts = jsonObj.getJSONArray("First");
                    //for (int i = 0; i < contacts.length(); i++) {
                    //    JSONObject c = contacts.getJSONObject(i);

                    //    todo_id = c.getString("events_id");
                    //   description = c.getString("events_description");

                    //}








                    //Intent intent = new Intent(Ambitionpage.this,Mainpage.class);
                    //intent.putExtra("id1",todo_id);
                    //intent.putExtra("id2",extra);
                    //intent.putExtra("desc1",description);
                    //intent.putExtra("desc2",extradescription);
                    //intent.putExtra("ambition",ambition);
                    //intent.putExtra("ug",ug_desc);
                    //intent.putExtra("pg",pg_desc);
                    //startActivity(intent);


                }catch (final JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("latitude",latitude);
                params.put("longitude",longitude);
                params.put("number",phonenumber);
                params.put("landmark",landmark);
                params.put("reason",reason);
                params.put("victims",victims);

                return params;
            }
        };
        MySingleton.getInstance(Accident_Information.this).addToRequestque(stringRequest);

















    }

}
