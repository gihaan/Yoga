package com.example.gihan.yoga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button exercise,setting,callender;
    private ImageView btnTraining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exercise=(Button)findViewById(R.id.btnExersise);
        setting=(Button)findViewById(R.id.btnSetting);
        callender=(Button)findViewById(R.id.calender);

        btnTraining=(ImageView)findViewById(R.id.btnTraining);
        btnTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setting_intent=new Intent(getApplicationContext(),DailyTraining.class);
                startActivity(setting_intent);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setting_intent=new Intent(getApplicationContext(),SettingPage.class);
                startActivity(setting_intent);
            }
        });

        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe_intent=new Intent(getApplicationContext(),ListExercises.class);
                startActivity(exe_intent);
            }
        });
        callender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exe_intent=new Intent(getApplicationContext(),CalenderActivity.class);
                startActivity(exe_intent);
            }
        });

    }
}
