package com.example.user.project;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class setWorkoutTimeFragment extends Fragment {

    private static final String TAG = "setWorkoutTimeFragment";
    private TextView textView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.setworkouttime_fragment, container, false);

        textView = (TextView)view.findViewById(R.id.alarmSetTextView);
        Button pickTime = (Button) view.findViewById(R.id.pickTimeBtn);
        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerFragment newFragment = new TimePickerFragment();
                newFragment.setListener(mOnTimeSetListener);
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });


        return view;
    }


    TimePickerDialog.OnTimeSetListener mOnTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

//            Calendar c = Calendar.getInstance();
//            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
//            c.set(Calendar.MINUTE, minute);
//            c.set(Calendar.SECOND, 0);
//
//            updateTime(c);
//            startAlarm(c);
            textView.setText("Hours: " + hourOfDay + " Minutes: " + minute);

        }


    };





}
