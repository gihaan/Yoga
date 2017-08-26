package com.example.gihan.yoga.Data;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.HashSet;

/**
 * Created by Gihan on 8/25/2017.
 */

public class WorkOutDone implements DayViewDecorator {

    HashSet<CalendarDay>list;
    ColorDrawable colorDrawable;

    public WorkOutDone(HashSet<CalendarDay> list) {
        this.list = list;
        colorDrawable=new ColorDrawable(Color.parseColor("#e57373"));
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return list.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(colorDrawable);

    }
}
