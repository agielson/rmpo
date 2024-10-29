package com.example.lab4;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDataBase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATA_BASE_NAME = "laptop.db";
    private static final int DATA_BASE_VERSION = 1;

    private static final String TABLE_NAME = "my_laptop";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME_OF_CPU = "name_of_cpu";
    private static final String COLUMN_DIAGONAL = "laptop_diagonal";
    private static final String COLUMN_VIDEO_CARD = "availability_of_video_card";
    private static final String COLUMN_OS = "operating_system";
    private static final String COLUMN_PRICE = "price";




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
                + COLUMN_OS + " TEXT, "
                + COLUMN_PRICE + " REAL);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    void addLaptop(String cpu, int diagonal,String video_card,String os,int price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME_OF_CPU,cpu);
        cv.put(COLUMN_DIAGONAL,diagonal);
        cv.put(COLUMN_VIDEO_CARD,video_card);
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
}
