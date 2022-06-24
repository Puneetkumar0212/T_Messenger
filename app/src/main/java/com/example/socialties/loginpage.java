package com.example.socialties;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginpage extends AppCompatActivity {
        private TextView email;
        private TextView password;
        private Button login ;
        private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email= email.getText().toString();
                String txt_password=password.getText().toString();

                loginuser(txt_email,txt_password);
            }
        });
    }

    private void loginuser(String txt_email, String txt_password) {
        auth.signInWithEmailAndPassword(txt_email,txt_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(loginpage.this, "Logging Successfull", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(loginpage.this,Start.class));
                }
                else
                {
                    Toast.makeText(loginpage.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}