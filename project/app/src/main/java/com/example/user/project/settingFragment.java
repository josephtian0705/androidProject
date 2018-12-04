package com.example.user.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class settingFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.setting_fragment,container,false);

        Button profileEditButton = (Button)view.findViewById(R.id.profileEditBtn);
        Button setWorkoutTimeButton = (Button)view.findViewById(R.id.setWorkoutTimeBtn);
        Button logOutButton = (Button) view.findViewById(R.id.logOutButton);


        profileEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragment = getFragmentManager().beginTransaction();
                fragment.replace(R.id.fragment_container,new profileEditFragment() );
                fragment.commit();
            }
        });

        setWorkoutTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fragment = getFragmentManager().beginTransaction();
                fragment.replace(R.id.fragment_container,new setWorkoutTimeFragment() );
                fragment.commit();

            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);

            }
        });

        return view;
    }


}
