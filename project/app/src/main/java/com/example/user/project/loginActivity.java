package com.example.user.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class loginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button loginButton;
    private EditText loginEmail;
    private EditText loginPassword;

    private FirebaseAuth firebaseAuth;


    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Toast.makeText(this, "Welcome To Fitness!", Toast.LENGTH_SHORT).show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        loginButton = (Button) findViewById(R.id.buttonLogin);
        loginEmail = (EditText) findViewById(R.id.editTextEmail);
        loginPassword = (EditText) findViewById(R.id.editTextPassword);

        progressDialog = new ProgressDialog(this);

        loginButton.setOnClickListener(this);

    }

    //Login function
    private void loginFunction(){
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please Enter Your Password",Toast.LENGTH_SHORT).show();
            return;

        }

        else {

            progressDialog.setMessage("Login in progress... Please wait");
            progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                        if(firebaseAuth.getCurrentUser() != null){

                            progressDialog.dismiss();
                            startActivity(new Intent(getApplicationContext(),homepageActivity.class));
                        }

                    } else {

                        progressDialog.dismiss();
                        Toast.makeText(loginActivity.this, "Login failed...", Toast.LENGTH_SHORT).show();



                    }

                }
            });
        }

    }

    @Override
    public void onClick(View v) {

        if(v == loginButton){

            loginFunction();
        }
    }
}
