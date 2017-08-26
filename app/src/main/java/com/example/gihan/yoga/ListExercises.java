package com.example.gihan.yoga;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gihan.yoga.Data.Exercise;
import com.example.gihan.yoga.Data.RecyclewViewAdapter;

import java.util.ArrayList;

public class ListExercises extends AppCompatActivity {

     ArrayList<Exercise>mList=new ArrayList<>();
    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclewViewAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercises);

        initData();
        
        mRecyclerView=(RecyclerView)findViewById(R.id.list_exercises);
        layoutManager=new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter=new RecyclewViewAdapter(mList,getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);


    }

    private void initData() {

        mList.add(new Exercise(R.drawable.easy_pose,"Easy Pose"));
        mList.add(new Exercise(R.drawable.cobra_pose,"Cobra Pose"));
        mList.add(new Exercise(R.drawable.downward_facing_dog,"Down Facing Dog"));
        mList.add(new Exercise(R.drawable.half_pigeon,"Half Pigeon"));
        mList.add(new Exercise(R.drawable.low_lunge,"Low Lunge"));
        mList.add(new Exercise(R.drawable.upward_bow,"UpWard Bow"));
        mList.add(new Exercise(R.drawable.crescent_lunge,"Crescent Lunge"));
        mList.add(new Exercise(R.drawable.warrior_pose,"Wairror Pose"));
        mList.add(new Exercise(R.drawable.bow_pose,"Bow pose"));
        mList.add(new Exercise(R.drawable.warrior_pose_2,"Wairror Pose 2"));


    }
}
