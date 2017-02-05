package com.nagajothy.smazee.emergency;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NumberActivity extends AppCompatActivity {
    Button submit;
    EditText number;
    String temp_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        submit = (Button)findViewById(R.id.number_button);
        number = (EditText)findViewById(R.id.number_edit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp_number = number.getText().toString();
                Intent verify = new Intent(NumberActivity.this,VerificationActivity.class);
                verify.putExtra("Temp Number",temp_number);
                startActivity(verify);
            }
        });
    }
}
