package com.example.socialties;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Start extends AppCompatActivity {

    private Button logout;
    private TextView number;
    private TextView message;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        logout = findViewById(R.id.button5);
        number = findViewById(R.id.editTextPhone);
        message = findViewById(R.id.editTextTextPersonName2);
        send = findViewById(R.id.button6);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sms_number = number.getText().toString().trim();
                String sms = message.getText().toString().trim();


                if (ContextCompat.checkSelfPermission(Start.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    sendsms();
                } else {
                    ActivityCompat.requestPermissions(Start.this, new String[]{Manifest.permission.SEND_SMS}, 0);
                }
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Start.this, "Logout Sucessfull", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Start.this, MainActivity.class));
            }
        });
    }


    private void sendsms() {
        String sms_number = number.getText().toString().trim();
        String sms = message.getText().toString().trim();

        if (!number.getText().toString().equals("") && !message.getText().toString().equals("")) {
            SmsManager smsmanager = SmsManager.getDefault();
            smsmanager.sendTextMessage(sms_number, null, sms, null, null);

            Toast.makeText(this, "Send Sucessfull", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Enter the above delails", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {

            case 0:

                if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    ;

            {
                sendsms();
            }
        }
    }
}
