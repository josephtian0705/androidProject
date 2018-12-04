package com.example.user.project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class changePasswordFragment extends Fragment {

    private EditText changePassword;
    private Button changePasswordButton;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.password_fragment, container, false);

        changePassword = (EditText) view.findViewById(R.id.changePasswordEditText);
        changePasswordButton = (Button) view.findViewById(R.id.changePasswordBtn);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String passwordChanged = changePassword.getText().toString().trim();

                if(!TextUtils.isEmpty(passwordChanged)) {

                    firebaseUser.updatePassword(passwordChanged).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {

                                Toast.makeText(getActivity(), "You password has changed", Toast.LENGTH_SHORT).show();

                            } else {

                                Toast.makeText(getActivity(), "There is a problem, your password cannot be changed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {

                    Toast.makeText(getActivity(),"Your password cannot blank", Toast.LENGTH_SHORT).show();

                }
            }
        });


        return view;
    }
}
