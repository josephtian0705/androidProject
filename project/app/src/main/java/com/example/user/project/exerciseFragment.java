package com.example.user.project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.project.Adapter.RecycleViewAdapter;
import com.example.user.project.Model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class exerciseFragment extends Fragment {

    List<Exercise> exerciseList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecycleViewAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

            View view = inflater.inflate(R.layout.exercise_fragment, container, false);


            initData();

            recyclerView = (RecyclerView) view.findViewById(R.id.list_ex);
            adapter = new RecycleViewAdapter(exerciseList,getActivity());
            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);


        return view;
    }

    private void initData(){

        exerciseList.add(new Exercise(R.drawable.easy_pose,"Easy Pose"));
        exerciseList.add(new Exercise(R.drawable.boat_pose,"Boat Pose"));
        exerciseList.add(new Exercise(R.drawable.cobra_pose,"Cobra Pose"));
        exerciseList.add(new Exercise(R.drawable.downward_facing_dog,"Downward Facing Dog"));
        exerciseList.add(new Exercise(R.drawable.half_pigeon,"Half Pigeon"));
        exerciseList.add(new Exercise(R.drawable.low_lunge,"Low Lunge"));
        exerciseList.add(new Exercise(R.drawable.upward_bow,"Upward Bow"));
        exerciseList.add(new Exercise(R.drawable.crescent_lunge,"Crescent Lunge"));
        exerciseList.add(new Exercise(R.drawable.bow_pose,"Bow Pose"));
        exerciseList.add(new Exercise(R.drawable.warrior_pose,"Warrior Pose"));
        exerciseList.add(new Exercise(R.drawable.warrior_pose_2,"Warrior Pose 2"));


    }
}
