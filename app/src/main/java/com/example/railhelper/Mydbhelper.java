package com.example.railhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Mydbhelper extends SQLiteOpenHelper {
    private static final String DATABASE = "TRAIN.db";
    private static final String TABLE1 = "EASTZONE";
    private static final String TABLE2 = "WESTZONE";
    private static final String ID = "ID";
    private static final String TRAIN_NAME = "TRAIN_NAME";
    private static final String WHDAY = "WEEKLY_HOLIDAY";
    private static final String ISTATION = "INITIAL_STATION";
    private static final String ITIME = "INITIAL_TIME";
    private static final String DSTATION = "DESTINATION_STATION";
    private static final String DTIME = "DESTINATION_TIME";
    private static final int VERSION_NUMBER = 4;
    private static final String CREATE_TABLE1 = "CREATE TABLE "+TABLE1+" ("+ID+" INTEGER PRIMARY KEY, "+TRAIN_NAME+" VARCHAR(255), "+WHDAY+" VARCHAR(255), "+ISTATION+" VARCHAR(255), "+ITIME+" VARCHAR(255), "+DSTATION+" VARCHAR(255), "+DTIME+" VARCHAR(255));";
    private static final String CREATE_TABLE2 = "CREATE TABLE "+TABLE2+" ("+ID+" INTEGER PRIMARY KEY, "+TRAIN_NAME+" VARCHAR(255), "+WHDAY+" VARCHAR(255), "+ISTATION+" VARCHAR(255), "+ITIME+" VARCHAR(255), "+DSTATION+" VARCHAR(255), "+DTIME+" VARCHAR(255));";
    private static final String DROP_TABLE1 = "DROP TABLE IF EXISTS "+TABLE1+" ";
    private static final String DROP_TABLE2 = "DROP TABLE IF EXISTS "+TABLE2+" ";
    private static final String DISPLAY1 = "SELECT * FROM "+TABLE1+"";
    private static final String DISPLAY2 = "SELECT * FROM "+TABLE2+"";
    private Context context;


    public Mydbhelper(@Nullable Context context) {
        super(context, DATABASE, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//       try{
//            Toast.makeText(context, "onCreate is called", Toast.LENGTH_LONG).show();
//            db.execSQL(CREATE_TABLE1);
//            db.execSQL(CREATE_TABLE2);
//        }
//        catch (Exception e){
//            Toast.makeText(context, "Exception :"+e, Toast.LENGTH_LONG).show();
//        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        try {
//            Toast.makeText(context, "onUpgrade is called", Toast.LENGTH_LONG).show();
//            db.execSQL(DROP_TABLE1);
//            db.execSQL(DROP_TABLE2);
//            onCreate(db);
//        }
//        catch (Exception e){
//            Toast.makeText(context, "Exception :"+e, Toast.LENGTH_LONG).show();
//        }
   }


    public Cursor display1(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(DISPLAY1, null);
        return cursor;
    }

    public Cursor display2(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(DISPLAY2, null);
        return cursor;
    }
    public Cursor searchData(String name){
        name = name.toUpperCase();
//        boolean isFound = name.contains("EXPRESS");
//        if(isFound!=true){
//            name = name + " EXPRESS";
//        }
        name = "\"" +name+"%"+ "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE1+" WHERE "+TRAIN_NAME+" LIKE "+name+" UNION SELECT * FROM "+TABLE2+" WHERE "+TRAIN_NAME+" LIKE "+name+"",null);
        return cursor;
    }
}
