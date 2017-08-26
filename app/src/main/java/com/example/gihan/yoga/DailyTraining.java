package com.example.gihan.yoga;

import android.app.ProgressDialog;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gihan.yoga.Data.Common;
import com.example.gihan.yoga.Data.Exercise;
import com.example.gihan.yoga.Data.SharedPrefrencess;
import com.example.gihan.yoga.Data.YogaDB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

import static com.example.gihan.yoga.Data.Common.TIME_LIMIT_EASY;
import static com.example.gihan.yoga.Data.Common.TIME_LIMIT_HARD;
import static com.example.gihan.yoga.Data.Common.TIME_LIMIT_MEDUIM;

public class DailyTraining extends AppCompatActivity {

    private Button btnStart;
    private TextView getReady, cutDown, txtTimer, ex_name;
    private ImageView exe_image;
    LinearLayout layoutGetReady;
    List<Exercise> mList = new ArrayList<>();
    private MaterialProgressBar progressDialog;
    private SharedPrefrencess da;

    int exe_id = 0;
    private int limit_time = 0;

    YogaDB yogaDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_training);

        initData();

        yogaDB = new YogaDB(this);
        da = new SharedPrefrencess(this);


        btnStart = (Button) findViewById(R.id.daily_training_btn_start_);
        getReady = (TextView) findViewById(R.id.text_get_ready);
        cutDown = (TextView) findViewById(R.id.text_cutdown);
        txtTimer = (TextView) findViewById(R.id.daily_training_timer);
        ex_name = (TextView) findViewById(R.id.daily_trainig_name_exrcise);
        exe_image = (ImageView) findViewById(R.id.daily_trainig_image_exercise);
        progressDialog = (MaterialProgressBar) findViewById(R.id.progressbar);

        layoutGetReady = (LinearLayout) findViewById(R.id.layout_get_ready);

        progressDialog.setMax(mList.size());

        setExerciseInformation(exe_id);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnStart.getText().toString().toLowerCase().equals("start")) {
                    showGetready();
                    btnStart.setText("Done");
                } else if (btnStart.getText().toString().toLowerCase().equals("done")) {

                    if (da.returnmode() == 0) {
                        exerciseEwasyCutDown.cancel();

                    } else if (da.returnmode() == 1) {
                        exerciseMeduimCutDown.cancel();

                    } else if (da.returnmode() == 2) {
                        exerciseHardCutDown.cancel();

                    }
                    reseTimeCountDown.cancel();

                    if (exe_id < mList.size()) {
                        showResetTime();

                        exe_id++;
                        progressDialog.setProgress(exe_id);
                        txtTimer.setText("");
                    }
                } else if (btnStart.getText().toString().toLowerCase().equals("skip")) {


                    if (da.returnmode() == 0) {
                        exerciseEwasyCutDown.cancel();

                    } else if (da.returnmode() == 1) {
                        exerciseMeduimCutDown.cancel();

                    } else if (da.returnmode() == 2) {
                        exerciseHardCutDown.cancel();

                    }

                    reseTimeCountDown.cancel();
                    txtTimer.setVisibility(View.VISIBLE);

                    btnStart.setText("Done");
                    exe_image.setVisibility(View.VISIBLE);
                    btnStart.setVisibility(View.VISIBLE);
                    layoutGetReady.setVisibility(View.INVISIBLE);

                    if (da.returnmode() == 0) {
                        exerciseEwasyCutDown.start();

                    } else if (da.returnmode() == 1) {
                        exerciseMeduimCutDown.start();

                    } else if (da.returnmode() == 2) {
                        exerciseHardCutDown.start();

                    }


                    if (exe_id < mList.size()) {
                        exe_image.setImageResource(mList.get(exe_id).getImage_id());
                        ex_name.setText(mList.get(exe_id).getName());


                    }else {
                        showFinished();
                    }


                } else if (exe_id == mList.size()) {
                    showFinished();

                }


            }
        });


    }

    //-------------------------------------------------------
    private void showResetTime() {

        exe_image.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);

        btnStart.setText("Skip");
        btnStart.setVisibility(View.VISIBLE);
        layoutGetReady.setVisibility(View.VISIBLE);


        reseTimeCountDown.start();
        getReady.setText("Reset Time");

    }


    //-------------------------------------------------------
    private void showGetready() {
        exe_image.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.VISIBLE);

        layoutGetReady.setVisibility(View.VISIBLE);

        getReady.setText("GetReady");

        new CountDownTimer(6000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                cutDown.setText("" + (millisUntilFinished - 1000) / 1000);
            }

            @Override
            public void onFinish() {
                showExercises();

            }
        }.start();

    }


    //-------------------------------------------------------
    private void showExercises() {
        if (exe_id < mList.size()) {
            exe_image.setVisibility(View.VISIBLE);
            btnStart.setVisibility(View.VISIBLE);
            layoutGetReady.setVisibility(View.INVISIBLE);

            if (da.returnmode() == 0) {
                exerciseEwasyCutDown.start();

            } else if (da.returnmode() == 1) {
                exerciseMeduimCutDown.start();

            } else if (da.returnmode() == 2) {
                exerciseHardCutDown.start();

            }

            //--------------DATA OF EXERSICES NAME AND IMAGE----------------------
            exe_image.setImageResource(mList.get(exe_id).getImage_id());
            ex_name.setText(mList.get(exe_id).getName());

        } else {
            showFinished();
        }
    }
    //-------------------------------------------------------

    private void showFinished() {
        exe_image.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        layoutGetReady.setVisibility(View.VISIBLE);
        progressDialog.setVisibility(View.INVISIBLE);
        ex_name.setVisibility(View.INVISIBLE);

        getReady.setText("Finish !!!");
        cutDown.setText("Congrateulations !!! \n you are done with today exercise ");
        cutDown.setTextSize(20);

        //---------------------------SAVE DATAT TO DATABASE------------------------------
        yogaDB.saveDay("" + Calendar.getInstance().getTimeInMillis());

    }

    ////////-------CUT DOWN---------------------------------------
    CountDownTimer exerciseEwasyCutDown = new CountDownTimer(TIME_LIMIT_EASY, 1000) {
        @Override
        public void onTick(long l) {
            txtTimer.setText("" + (l / 1000));
            btnStart.setText("Done");

        }

        @Override
        public void onFinish() {
            if (exe_id < mList.size() - 1) {
                exe_id++;
                progressDialog.setProgress(exe_id);
                txtTimer.setText("");

                setExerciseInformation(exe_id);
                btnStart.setText("start");
            } else {
                showFinished();
            }
        }
    };
    CountDownTimer exerciseMeduimCutDown = new CountDownTimer(TIME_LIMIT_MEDUIM, 1000) {
        @Override
        public void onTick(long l) {
            txtTimer.setText("" + (l / 1000));
            btnStart.setText("Done");

        }

        @Override
        public void onFinish() {
            if (exe_id < mList.size() - 1) {
                exe_id++;
                progressDialog.setProgress(exe_id);
                txtTimer.setText("");

                setExerciseInformation(exe_id);
                btnStart.setText("start");
            } else {
                showFinished();
            }
        }
    };
    CountDownTimer exerciseHardCutDown = new CountDownTimer(TIME_LIMIT_HARD, 1000) {
        @Override
        public void onTick(long l) {
            txtTimer.setText("" + (l / 1000));
            btnStart.setText("Done");
        }

        @Override
        public void onFinish() {
            if (exe_id < mList.size() - 1) {
                exe_id++;
                progressDialog.setProgress(exe_id);
                txtTimer.setText("");

                setExerciseInformation(exe_id);
                btnStart.setText("start");
            } else {
                showFinished();
            }
        }
    };


    //-------------------------------------------------------
    CountDownTimer reseTimeCountDown = new CountDownTimer(10000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            txtTimer.setText("" + (millisUntilFinished / 1000));

        }

        @Override
        public void onFinish() {

            setExerciseInformation(exe_id);
            showExercises();
        }

    };

    private void initData() {

        mList.add(new Exercise(R.drawable.easy_pose, "Easy Pose"));
        mList.add(new Exercise(R.drawable.cobra_pose, "Cobra Pose"));
        mList.add(new Exercise(R.drawable.downward_facing_dog, "Down Facing Dog"));
        mList.add(new Exercise(R.drawable.half_pigeon, "Half Pigeon"));
        mList.add(new Exercise(R.drawable.low_lunge, "Low Lunge"));
        mList.add(new Exercise(R.drawable.upward_bow, "UpWard Bow"));
        mList.add(new Exercise(R.drawable.crescent_lunge, "Crescent Lunge"));
        mList.add(new Exercise(R.drawable.warrior_pose, "Wairror Pose"));
        mList.add(new Exercise(R.drawable.bow_pose, "Bow pose"));
        mList.add(new Exercise(R.drawable.warrior_pose_2, "Wairror Pose 2"));


    }
    //---

    public void setExerciseInformation(int id) {
        exe_image.setImageResource(mList.get(id).getImage_id());
        ex_name.setText(mList.get(id).getName());

        btnStart.setText("Start");

        exe_image.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        txtTimer.setVisibility(View.VISIBLE);

        layoutGetReady.setVisibility(View.INVISIBLE);


    }
}
