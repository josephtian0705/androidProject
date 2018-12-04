package com.example.user.project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class profileEditFragment extends Fragment {


    private EditText usernameEditText, ageEditText;
    private TextView genderSpinner;
    private Button editButton, changePasswordPage;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference dbReference;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profileedit_fragment, container, false);

        usernameEditText = (EditText) view.findViewById(R.id.usernameEdit);
        ageEditText = (EditText) view.findViewById(R.id.ageEdit);
        genderSpinner = (TextView) view.findViewById(R.id.genderSpinner);
        editButton = (Button) view.findViewById(R.id.saveButton);
        changePasswordPage = (Button)  view.findViewById(R.id.changePassword);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        final String userId = firebaseAuth.getCurrentUser().getUid();
        dbReference = firebaseDatabase.getReference("User").child(userId);


//        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.gender, android.R.layout.simple_spinner_item);
//        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        genderSpinner.setAdapter(genderAdapter);
//        genderSpinner.setOnItemSelectedListener(this);


        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                user user = dataSnapshot.getValue(user.class);

                usernameEditText.setText(user.getUsername());
                ageEditText.setText(user.getAge());
                genderSpinner.setText(user.getGender());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameEditText.getText().toString().trim();
                String age = ageEditText.getText().toString().trim();
                String gender = genderSpinner.getText().toString();

                if(TextUtils.isEmpty(username)){

                    Toast.makeText(getActivity(),"Username cannot empty", Toast.LENGTH_SHORT).show();
                }

                else if(TextUtils.isEmpty(age)){

                    Toast.makeText(getActivity(),"Age cannot empty", Toast.LENGTH_SHORT).show();
                }

                else {

                    user user = new user(username, age, gender);

                    dbReference.setValue(user);

                    Toast.makeText(getActivity(), "Your profile has changed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        changePasswordPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fragment = getFragmentManager().beginTransaction();
                fragment.replace(R.id.fragment_container,new changePasswordFragment());
                fragment.commit();
            }
        });



        return view;
    }

//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//        String gender = parent.getItemAtPosition(position).toString();
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//        Toast.makeText(getActivity(), "Choose Gender", Toast.LENGTH_SHORT).show();
//
//    }
}
