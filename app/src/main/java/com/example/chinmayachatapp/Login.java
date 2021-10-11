//created by chinmaya
package com.example.chinmayachatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {

    TextView signup11;
    EditText email, password;
    TextView signIn;
    FirebaseAuth auth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("wait we are authonicate you with chinmaya's Database..");
        progressDialog.setCancelable(false);

        auth = FirebaseAuth.getInstance();
        signup11= findViewById(R.id.signup);

        signIn = findViewById(R.id.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                String email1 = email.getText().toString();
                String password2 = password.getText().toString();

                if (TextUtils.isEmpty(email1) || TextUtils.isEmpty(password2)) {
                    progressDialog.dismiss();
                    Toast.makeText(Login.this, "Enter Valid Data", Toast.LENGTH_SHORT).show();
                } else if (!email1.matches(emailPattern)) {
                    progressDialog.dismiss();
                    email.setError("Invalid Email");
                    Toast.makeText(Login.this, "Envalid Email", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    progressDialog.dismiss();
                    password.setError("Invalid Password");
                    Toast.makeText(Login.this, "Please enter valid password", Toast.LENGTH_SHORT).show();
                } else {
                    auth.signInWithEmailAndPassword(email1, password2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                startActivity(new Intent(Login.this, home.class));
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(Login.this, "Error in login", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        signup11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, signup.class));
            }
        });
    }
}




