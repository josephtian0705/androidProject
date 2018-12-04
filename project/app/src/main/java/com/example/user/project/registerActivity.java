package com.example.user.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registerActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Button registerButton;

    private EditText registerEmail;
    private EditText registerPassword;
    private EditText registerConfirmPassword;
    private EditText registerUsername;
    private Spinner registerGender;
    private EditText registerAge;


    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    DatabaseReference databasePostUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        registerButton = (Button) findViewById(R.id.buttonRegister);

        registerEmail = (EditText) findViewById(R.id.editTextEmail);
        registerPassword = (EditText) findViewById(R.id.editTextPassword);
        registerConfirmPassword = (EditText) findViewById(R.id.confirmPassword);

        registerUsername = (EditText) findViewById(R.id.editTextUsername);
        registerGender = (Spinner) findViewById(R.id.genderSpinner);
        registerAge = (EditText) findViewById(R.id.editTextAge);

        databasePostUser = FirebaseDatabase.getInstance().getReference("User");

        registerButton.setOnClickListener(this);


        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        registerGender.setAdapter(genderAdapter);
        registerGender.setOnItemSelectedListener(this);


    }


    //Register Function
    private void registerUser(){
        final String email = registerEmail.getText().toString().trim();
        final String password = registerPassword.getText().toString().trim();
        String confirmPassword = registerConfirmPassword.getText().toString().trim();
        final String username = registerUsername.getText().toString().trim();
        final String age = registerAge.getText().toString();
        final String gender = registerGender.getSelectedItem().toString();


        if(TextUtils.isEmpty(username)) {

            Toast.makeText(registerActivity.this, "Username is empty", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(email)){

            Toast.makeText(this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(password)){

            Toast.makeText(this,"Please Enter Your Password",Toast.LENGTH_SHORT).show();

        }
        else if(!password.equals(confirmPassword)){

            Toast.makeText(registerActivity.this, "Both password are not the same", Toast.LENGTH_LONG).show();

        }
        else if(TextUtils.isEmpty(age)){

            Toast.makeText(registerActivity.this, "Age is empty", Toast.LENGTH_SHORT).show();

        }
        else {

            progressDialog.setMessage("Registering User......");
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {

                        progressDialog.dismiss();
                        if(firebaseAuth.getCurrentUser() != null){

                            String userId = firebaseAuth.getCurrentUser().getUid();

                            user user = new user(username, age, gender);

                            databasePostUser.child(userId).setValue(user);

                            startActivity(new Intent(getApplicationContext(),homepageActivity.class));

                        } else {

                            Toast.makeText(registerActivity.this,"Account doesn't exist...",Toast.LENGTH_SHORT).show();
                        }


                    } else {

                        progressDialog.dismiss();
                        Toast.makeText(registerActivity.this, "Account cannot register... Please try again", Toast.LENGTH_SHORT).show();

                    }

                }
            });

        }
    } // end of register function



    @Override
    public void onClick(View v) {

        if(v == registerButton){
            registerUser();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String gender = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Choose Gender", Toast.LENGTH_SHORT).show();
    }
}
