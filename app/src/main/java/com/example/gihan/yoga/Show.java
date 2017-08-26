package com.example.gihan.yoga;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gihan.yoga.Data.SharedPrefrencess;

import static com.example.gihan.yoga.Data.Common.TIME_LIMIT_EASY;
import static com.example.gihan.yoga.Data.Common.TIME_LIMIT_HARD;
import static com.example.gihan.yoga.Data.Common.TIME_LIMIT_MEDUIM;

public class Show extends AppCompatActivity {

    int imageId;
    String name;
    private boolean isRunning=false;
    private TextView mExerciseName;
    private ImageView mExerciseImage;
    private TextView mTimer;
    private Button mStart;
    SharedPrefrencess da;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        mExerciseImage=(ImageView)findViewById(R.id.detail_image_exercise);
        mExerciseName=(TextView)findViewById(R.id.detail_name_exrcise);
        mTimer=(TextView)findViewById(R.id.timer);
        mStart=(Button)findViewById(R.id.btn_start);

        da=new SharedPrefrencess(getApplicationContext());


        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStart.setText("DONE ");

                if( !isRunning) {

                    int timeLimit=0;
                    if(da.returnmode()==0){
                        timeLimit=TIME_LIMIT_EASY;
                    }
                    if(da.returnmode()==1){
                        timeLimit=TIME_LIMIT_MEDUIM;
                    }
                    if(da.returnmode()==2){
                        timeLimit=TIME_LIMIT_HARD;
                    }

                    new CountDownTimer(timeLimit, 1000) {

                        @Override
                        public void onTick(long millisUntilFinished) {
                            mTimer.setText("" + millisUntilFinished/1000);

                        }

                        @Override
                        public void onFinish() {

                            Toast.makeText(getApplicationContext(), "Finish !!!!!", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    }.start();
                }else {
                    Toast.makeText(getApplicationContext(), "Finish !!!!!", Toast.LENGTH_SHORT).show();
                    finish();

                }
                isRunning = !isRunning;
            }
        });


        mTimer.setText("");

        if(getIntent() !=null){
            imageId=getIntent().getIntExtra("image_id",-1);
            name=getIntent().getStringExtra("name");

            mExerciseImage.setImageResource(imageId);
            mExerciseName.setText(name);

        }
    }
}
