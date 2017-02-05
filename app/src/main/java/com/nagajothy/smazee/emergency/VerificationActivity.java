package com.nagajothy.smazee.emergency;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class VerificationActivity extends AppCompatActivity {
    Button verify;
    EditText code;
    String verification_code , number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        verify = (Button)findViewById(R.id.verify_button);
        code = (EditText)findViewById(R.id.code_edit);
        Intent verifyintent = getIntent();
        number =  verifyintent.getStringExtra("Temp Number");


        //you have to check the code , then assign the number as the true value

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signin = new Intent(VerificationActivity.this , LoginActivity.class);
                signin.putExtra("Number",number);
                startActivity(signin);
            }
        });

    }
}
