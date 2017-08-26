package com.example.gihan.yoga.Data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Gihan on 8/24/2017.
 */

public class SharedPrefrencess {


    private Context mContext;

    public SharedPrefrencess(Context mContext){
        this.mContext=mContext;

    }

    public void savemode(int mode){

        SharedPreferences.Editor editor=mContext.getSharedPreferences("DateSettings.txt",Context.MODE_PRIVATE).edit();
        editor.putInt("mode",mode);
        editor.commit();

    }


    public int returnmode(){

        SharedPreferences editor=mContext.getSharedPreferences("DateSettings.txt",Context.MODE_PRIVATE);
        int mode=editor.getInt("mode",0);
        return mode;
    }
}
