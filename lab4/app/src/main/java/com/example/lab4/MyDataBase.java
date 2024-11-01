package com.example.lab4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDataBase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATA_BASE_NAME = "laptop.db";
    private static final int DATA_BASE_VERSION = 1;

    private static final String TABLE_NAME = "my_laptop";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME_OF_CPU = "name_of_cpu";
    public static final String COLUMN_DIAGONAL = "laptop_diagonal";
    public static final String COLUMN_VIDEO_CARD = "availability_of_video_card";
    public static final String COLUMN_VOLUME_OF_HARD_DATA = "volume_h_d";
    public static final String COLUMN_OS = "operating_system";
    public static final String COLUMN_PRICE = "price";




    public MyDataBase(@Nullable Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME +
                " ("+ COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME_OF_CPU + " TEXT, "
                + COLUMN_DIAGONAL + " REAL, "
                + COLUMN_VIDEO_CARD+ " TEXT, "
                + COLUMN_VOLUME_OF_HARD_DATA+ " REAL, "
                + COLUMN_OS + " TEXT, "
                + COLUMN_PRICE + " REAL);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //onCreate(db);

    }
    void addLaptop(String cpu, int diagonal,String video_card,int volume_hd,String os,int price)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME_OF_CPU,cpu);
        cv.put(COLUMN_DIAGONAL,diagonal);
        cv.put(COLUMN_VIDEO_CARD,video_card);
        cv.put(COLUMN_VOLUME_OF_HARD_DATA,volume_hd);
        cv.put(COLUMN_OS,os);
        cv.put(COLUMN_PRICE,price);
        long result = db.insert(TABLE_NAME,null,cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Sucessfully add", Toast.LENGTH_SHORT).show();
        }
    }
    public Cursor readAllData()
    {
        String query = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("readalldata", "open db");
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query,null);
            Log.d("readalldata", "open cursor ");

        }
        return cursor;

    }
}
