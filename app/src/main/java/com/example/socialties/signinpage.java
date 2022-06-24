package com.example.socialties;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signinpage extends AppCompatActivity {


    private EditText email;
    private EditText password;
    private Button signin ;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinpage);

        auth = FirebaseAuth.getInstance();
        email= findViewById(R.id.editTextTextEmailAddress2);
        password = findViewById(R.id.editTextTextPassword2);
        signin = findViewById(R.id.button2);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();

                if( TextUtils.isEmpty(txt_email)  || TextUtils.isEmpty(txt_password))
                {
                    Toast.makeText(signinpage.this, "Empty Credentials", Toast.LENGTH_SHORT).show();
                }
                else if(password.length() <6)
                {
                    Toast.makeText(signinpage.this, "Password is too Short", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    registeruser(txt_email,txt_password);
                }
            }
        });

    }

    private void registeruser( String txt_email, String txt_password) {
        auth.createUserWithEmailAndPassword( txt_email ,txt_password ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(signinpage.this, "Registering Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(signinpage.this,MainActivity.class));
                }
                else
                {
                    Toast.makeText(signinpage.this, "Registering Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}