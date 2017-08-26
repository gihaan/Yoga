package com.example.gihan.yoga;

import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gihan.yoga.Data.WorkOutDone;
import com.example.gihan.yoga.Data.YogaDB;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class CalenderActivity extends AppCompatActivity {


    private MaterialCalendarView materialCalendarView;
    private HashSet<CalendarDay> list=new HashSet<>();
    YogaDB yogaDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        yogaDB=new YogaDB(this);
        materialCalendarView=(MaterialCalendarView)findViewById(R.id.calender_view);

        List<String>workout=yogaDB.getWorkDay();
        HashSet<CalendarDay>convertList=new HashSet<>();

        for (String  value:workout){
            convertList.add(CalendarDay.from(new Date(Long.parseLong(value))));
            materialCalendarView.addDecorator(new WorkOutDone(convertList));

        }

    }
}


