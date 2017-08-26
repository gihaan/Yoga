package com.example.gihan.yoga.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static android.R.attr.phoneNumber;

/**
 * private final static String DATABASE_NAME = "numberdb";
 * private final static int DATABSE_VERSION = 1;
 * private final String CRATE = "create table " + DB_Contract.TABLE_NAME +
 * "(id integer primary key autoincrement," + DB_Contract.INCOMING_NUMBER +" text);";
 * private final static String DROP_TABLE = "drop table if exists " +DB_Contract.TABLE_NAME;
 * <p>
 * <p>
 * public Db_Helper(Context context) {
 * super(context, DATABASE_NAME, null, DATABSE_VERSION);
 * }
 *
 * @Override public void onCreate(SQLiteDatabase db) {
 * <p>
 * db.execSQL(CRATE);
 * <p>
 * }
 * @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 * db.execSQL(DROP_TABLE);
 * onCreate(db);
 * <p>
 * }
 * <p>
 * public void saveNumber(String phoneNumber,SQLiteDatabase database){
 * ContentValues contentValues=new ContentValues();
 * contentValues.put(DB_Contract.INCOMING_NUMBER,phoneNumber);
 * database.insert(DB_Contract.TABLE_NAME,null,contentValues);
 * }
 * <p>
 * public Cursor readNumber(SQLiteDatabase database){
 * <p>
 * String progection[]={"id",DB_Contract.INCOMING_NUMBER};
 * return database.query(DB_Contract.TABLE_NAME,progection,null,null,null,null,null);
 * <p>
 * <p>
 * }
 */

public class YogaDB extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "yoga";
    private final static int DATABSE_VERSION = 1;
    private final String CRATE = "create table work (id integer primary key autoincrement , day TEXT);";
    private final static String DROP_TABLE = "drop table if exists setting ";


    public YogaDB(Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CRATE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);


    }

    public void saveDay(String day) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("day", day);
        database.insert("work", null, contentValues);
    }

    public List<String> getWorkDay() {
        SQLiteDatabase database = getReadableDatabase();
        String progection[] = {"day"};
        Cursor c = database.query("work", progection, null, null, null, null, null);

        List<String> mList = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                mList.add(c.getString(c.getColumnIndex("day")));

            } while (c.moveToNext());

        }
        return mList;


    }


}
