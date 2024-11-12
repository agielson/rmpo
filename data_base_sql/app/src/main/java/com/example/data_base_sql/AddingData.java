package com.example.data_base_sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddingData extends AppCompatActivity implements View.OnClickListener {

    Button read_button, add_button, clear_button;
    EditText editText_id,editText_CPU,editText_diagonal,editText_video_card,editText_volume_hd,editText_os,editText_price;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adding_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        read_button = (Button)findViewById(R.id.read_button);
        read_button.setOnClickListener(this);

        add_button = (Button)findViewById(R.id.add_button);
        add_button.setOnClickListener(this);

        clear_button = (Button)findViewById(R.id.clear_button);
        clear_button.setOnClickListener(this);






        editText_id = (EditText) findViewById(R.id.editText_Id);
        editText_CPU = (EditText) findViewById(R.id.editText_CPU);
        editText_diagonal = (EditText) findViewById(R.id.editText_diagonal);
        editText_video_card = (EditText) findViewById(R.id.editText_video_card);
        editText_volume_hd = (EditText) findViewById(R.id.editText_volume_hd);
        editText_os = (EditText) findViewById(R.id.editText_os);
        editText_price = (EditText) findViewById(R.id.editText_price);

        dbHelper = new DBHelper(this);
    }
    public void onClick(View v){

        String id = editText_id.getText().toString();
        String cpu = editText_CPU.getText().toString();
        Integer diagonal = Integer.valueOf(editText_diagonal.getText().toString().trim());
        String video_card = editText_video_card.getText().toString();
        Integer volume_hd = Integer.valueOf(editText_volume_hd.getText().toString().trim());
        String os = editText_os.getText().toString();
        Integer price = Integer.valueOf(editText_price.getText().toString().trim());

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        switch (v.getId()){
            case R.id.read_button:
                Cursor cursor = database.query(DBHelper.TABLE_LAPTOP,null,null,null,null,null,null);

                if(cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int cpuIndex = cursor.getColumnIndex(DBHelper.KEY_CPU);
                    int diagonalIndex = cursor.getColumnIndex(DBHelper.KEY_DIAGONAL);
                    int videoIndex = cursor.getColumnIndex(DBHelper.KEY_VIDEO_CARD);
                    int volumeIndex = cursor.getColumnIndex(DBHelper.KEY_VOLUME_HD);
                    int osIndex = cursor.getColumnIndex(DBHelper.KEY_OS);
                    int priceIndex = cursor.getColumnIndex(DBHelper.KEY_PRICE);
                    do {
                        Log.d("mLog", "ID = " + cursor.getString(idIndex) +
                                ", cpu = " + cursor.getString(cpuIndex) +
                                ", diagonal = " + cursor.getInt(diagonalIndex) +
                                ", video card = " + cursor.getString(videoIndex) +
                                ", volume hard disk = " + cursor.getInt(volumeIndex) +
                                ", os  = " + cursor.getString(osIndex) +
                                ", price = " + cursor.getInt(priceIndex));
                    } while (cursor.moveToNext());
                } else
                    Log.d("myLog","0 rows");

                cursor.close();
                break;
            case R.id.add_button:
                contentValues.put(DBHelper.KEY_ID,id);
                contentValues.put(DBHelper.KEY_CPU,cpu);
                contentValues.put(DBHelper.KEY_DIAGONAL,diagonal);
                contentValues.put(DBHelper.KEY_VIDEO_CARD,video_card);
                contentValues.put(DBHelper.KEY_VOLUME_HD,volume_hd);
                contentValues.put(DBHelper.KEY_OS,os);
                contentValues.put(DBHelper.KEY_PRICE,price);

                database.insert(DBHelper.TABLE_LAPTOP,null,contentValues);
                break;
            case R.id.clear_button:
                database.delete(DBHelper.TABLE_LAPTOP,null,null);
                break;
        }
        dbHelper.close();
    }
}

