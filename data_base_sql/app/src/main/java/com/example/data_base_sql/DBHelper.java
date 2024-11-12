package com.example.data_base_sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "laptopDB";
    public static final String TABLE_LAPTOP = "laptop";

    public static final String KEY_ID = "_id";
    public static final String KEY_CPU = "cpu";
    public static final String KEY_DIAGONAL = "diagonal";
    public static final String KEY_VIDEO_CARD = "video_card";
    public static final String KEY_VOLUME_HD = "volume_hd";
    public static final String KEY_OS = "os";
    public static final String KEY_PRICE = "price";




    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_LAPTOP + "(" +KEY_ID + " integer primary key, "
        + KEY_CPU +" text, "+ KEY_DIAGONAL +" integer,"+ KEY_VIDEO_CARD +" text, "+ KEY_VOLUME_HD +" integer, "
        + KEY_OS +" text,"+KEY_PRICE+" integer "+")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists "+ TABLE_LAPTOP);

        onCreate(db);
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_LAPTOP+" Order by "+KEY_PRICE+" DESC ",null);
        return res;
    }
    public Cursor getSort2(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from ( "+"select * from  "+TABLE_LAPTOP+" Order by "+KEY_PRICE+" ) order by  "+KEY_OS,null);
        return res;
    }
    public Cursor getTotalPrice(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select SUM(price) from "+TABLE_LAPTOP,null);
        return res;
    }

    public Cursor getSortCheck(String data){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select AVG("+KEY_DIAGONAL+") from "+TABLE_LAPTOP+" order by "+ data,null);
        return res;
    }
}
