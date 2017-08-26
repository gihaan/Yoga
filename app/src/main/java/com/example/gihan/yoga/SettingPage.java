package com.example.gihan.yoga;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.gihan.yoga.Data.SharedPrefrencess;
import com.example.gihan.yoga.Data.YogaDB;

import java.util.Date;

public class SettingPage extends AppCompatActivity {
    private RadioButton mEasy, mMeduim, mHard;
    private RadioGroup group;
    private ToggleButton mToggleBtn;
    private Button mSaveBtn;
    private TimePicker mTimePicker;
    private YogaDB database;
    SharedPrefrencess da;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);

        mEasy = (RadioButton) findViewById(R.id.setting_radio_easy);
        mMeduim = (RadioButton) findViewById(R.id.setting_radio_meduim);
        mHard = (RadioButton) findViewById(R.id.setting_radio_hard);
        group = (RadioGroup) findViewById(R.id.setting_radio_group);

        mToggleBtn = (ToggleButton) findViewById(R.id.switch_button);
        mSaveBtn = (Button) findViewById(R.id.btn_save);
        mTimePicker = (TimePicker) findViewById(R.id.timebacker);

        da = new SharedPrefrencess(getApplicationContext());


        if (da.returnmode() + "" == null) {
            da.savemode(0);
        }
        int mode = da.returnmode();

        setRadioButton(mode);


        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMode();
                saveAlarm(mToggleBtn.isChecked());
                Toast.makeText(getApplication(), "Save  ...", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }

    private void saveAlarm(boolean checked) {
        if (checked) {

            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            Intent intent;
            PendingIntent pendingIntent;
            intent = new Intent(SettingPage.this, alarmNotificationReceiver.class);

            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

            //SET ALARM
            Calendar calende = Calendar.getInstance();
            Date todate = Calendar.getInstance().getTime();
            calende.set(todate.getYear(), todate.getMonth(), todate.getDay(), mTimePicker.getHour(), mTimePicker.getMinute());
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calende.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

            Log.d("debug in alarm",mTimePicker.getHour()+" : "+mTimePicker.getMinute());

        } else {
            //------------CANCEL ALARM
            Intent intent = new Intent(SettingPage.this, alarmNotificationReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            manager.cancel(pendingIntent);

        }

    }

    private void saveMode() {

        int selectMode = group.getCheckedRadioButtonId();

        if (selectMode == R.id.setting_radio_easy) {
            da.savemode(0);

        } else if (selectMode == R.id.setting_radio_meduim) {
            da.savemode(1);

        } else if (selectMode == R.id.setting_radio_hard) {
            da.savemode(2);

        }
    }

    public void setRadioButton(int mode) {


        if (mode == 0) {
            group.check(R.id.setting_radio_easy);

        } else if (mode == 1) {
            group.check(R.id.setting_radio_meduim);


        } else if (mode == 2) {
            group.check(R.id.setting_radio_hard);


        }


    }
}
