package com.example.data_base_sql;

import static com.example.data_base_sql.DBHelper.KEY_PRICE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity  {
    final String LOG_TAG = "myLog";
    Button sort1,sort2,sort3,sort4,sort5,sort6,sort7,sort8,button_add;
    DBHelper dbHelper;
    SQLiteDatabase db;
    EditText editInputNum;
    Intent num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbHelper = new DBHelper(this);
        sort1 = (Button) findViewById(R.id.sort1);
        sort2 = (Button) findViewById(R.id.sort2);
        sort3 = (Button) findViewById(R.id.sort3);
        sort4 = (Button) findViewById(R.id.sort4);
        sort5 = (Button) findViewById(R.id.sort5);
        sort6 = (Button) findViewById(R.id.sort6);
        sort7 = (Button) findViewById(R.id.sort7);
        sort8 = (Button) findViewById(R.id.sort8);

        button_add = (Button) findViewById(R.id.button_add);
        sortByPrice();
        sortBySameValue();
        addingData();
        getTotalSumPrice();
        getAVG();
        getMax();
        getMore();
        getLessThanAvg();
        getMore_1();

    }

    public void sortByPrice() {
        sort1.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view){
                        Cursor res = dbHelper.getData();
                        if(res.getCount()==0){
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Id : "+res.getString(0)+"\n");
                            buffer.append("CPU : "+res.getString(1)+"\n");
                            buffer.append("Diagonal : "+res.getString(2)+"\n");
                            buffer.append("Video Card : "+res.getString(3)+"\n");
                            buffer.append("Volume of hard disk : "+res.getString(4)+"\n");
                            buffer.append("OS : "+res.getString(5)+"\n");
                            buffer.append("Price : "+res.getString(6)+"\n\n");
                            myLog(res);
                            try {
                                writeInFile(buffer,"querry1.txt");
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                        Log.i("mLog"," \n----------------------------------------------------\n");


                        //showMessage("Laptop",buffer.toString());

                    }
                }
        );
    }
    public void sortBySameValue(){
        sort2.setOnClickListener(

                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Cursor res = dbHelper.getSort2();
                        if(res.getCount()==0){
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Id : "+res.getString(0)+"\n");
                            buffer.append("CPU : "+res.getString(1)+"\n");
                            buffer.append("Diagonal : "+res.getString(2)+"\n");
                            buffer.append("Video Card : "+res.getString(3)+"\n");
                            buffer.append("Volume of hard disk : "+res.getString(4)+"\n");
                            buffer.append("OS : "+res.getString(5)+"\n");
                            buffer.append("Price : "+res.getString(6)+"\n\n");
                            myLog(res);
                        }
                        Log.i("mLog"," \n----------------------------------------------------\n");

                        showMessage("Laptop",buffer.toString());
                    }
                }

        );

    }
    public void getTotalSumPrice(){
        sort3.setOnClickListener(

                new View.OnClickListener(){
                    @SuppressLint("Range")
                    @Override
                    public void onClick(View view) {
                        Cursor res = dbHelper.getTotalPrice();
                        if(res.getCount()==0){
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Price : "+res.getString(0)+"\n");
                            Log.d("mLog", buffer.toString());
                            try {
                                writeInFile(buffer,"querry2.txt");
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                        Log.i("mLog"," \n----------------------------------------------------\n");
                        //showMessage("Laptop",buffer.toString());
                    }

                }
        );

    }
    public void getAVG(){

        sort4.setOnClickListener(
                new View.OnClickListener(){
                    String col = "";
                    @Override
                    public void onClick(View view) {
                        RadioGroup radioGroup = findViewById(R.id.group);
                        int selectedId = radioGroup.getCheckedRadioButtonId();
                        switch (selectedId){
                            case R.id.radioButton_cpu:
                                col = "diagonal";
                                break;
                            case R.id.radioButton_diagonal:
                                col= "volume_hd";
                                break;
                            case R.id.radioButton_video:
                                col= "price";
                                break;
                        }
                        Cursor res = dbHelper.getSortCheck(col);
                        if(res.getCount()==0){
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append(col+" "+res.getString(0)+"\n");
                            Log.d("mLog", buffer.toString());
                            try {
                                writeInFile(buffer,"querry4.txt");
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }

                        }
                        Log.i("mLog"," \n----------------------------------------------------\n");
                        showMessage("Laptop",buffer.toString());


                    }
                }
        );

    }
    public void getMax(){
        sort5.setOnClickListener(

                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Cursor res = dbHelper.getMaxNum();
                        if(res.getCount()==0){
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Max num : "+res.getString(0)+"\n");
                            Log.d("mLog", buffer.toString());
                        }
                        Log.i("mLog"," \n----------------------------------------------------\n");
                        //showMessage("Laptop",buffer.toString());
                    }
                }
        );

    }
    public void getMore(){
        sort6.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        editInputNum = (EditText) findViewById(R.id.editTextInputNum);
                        Integer price = Integer.valueOf(editInputNum.getText().toString().trim());
                        Cursor res = dbHelper.getMoreThan(price,0);
                        if(res.getCount()==0){
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Id : "+res.getString(0)+"\n");
                            buffer.append("CPU : "+res.getString(1)+"\n");
                            buffer.append("Diagonal : "+res.getString(2)+"\n");
                            buffer.append("Video Card : "+res.getString(3)+"\n");
                            buffer.append("Volume of hard disk : "+res.getString(4)+"\n");
                            buffer.append("OS : "+res.getString(5)+"\n");
                            buffer.append("Price : "+res.getString(6)+"\n\n");
                            myLog(res);
                        }
                        Log.i("mLog"," \n----------------------------------------------------\n");

                        showMessage("Laptop",buffer.toString());
                    }
                }
        );

    }
    public void getLessThanAvg(){
        sort7.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Cursor res = dbHelper.showLessAvg();
                        if(res.getCount()==0){
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Id : "+res.getString(0)+"\n");
                            buffer.append("CPU : "+res.getString(1)+"\n");
                            buffer.append("Diagonal : "+res.getString(2)+"\n");
                            buffer.append("Video Card : "+res.getString(3)+"\n");
                            buffer.append("Volume of hard disk : "+res.getString(4)+"\n");
                            buffer.append("OS : "+res.getString(5)+"\n");
                            buffer.append("Price : "+res.getString(6)+"\n\n");
                            myLog(res);
                        }

                        Log.i("mLog"," \n----------------------------------------------------\n");
                        showMessage("Laptop",buffer.toString());
                    }
                }
        );

    }
    public void getMore_1(){
        sort8.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        editInputNum = (EditText) findViewById(R.id.editTextInputNum);
                        Integer price = Integer.valueOf(editInputNum.getText().toString().trim());
                        Cursor res = dbHelper.getMoreThan(price,1);
                        if(res.getCount()==0){
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Id : "+res.getString(0)+"\n");
                            buffer.append("CPU : "+res.getString(1)+"\n");
                            buffer.append("Diagonal : "+res.getString(2)+"\n");
                            buffer.append("Video Card : "+res.getString(3)+"\n");
                            buffer.append("Volume of hard disk : "+res.getString(4)+"\n");
                            buffer.append("OS : "+res.getString(5)+"\n");
                            buffer.append("Price : "+res.getString(6)+"\n\n");
                            Log.i("mLog"," \n----------------------------------------------------\n");
                            myLog(res);
                        }
                        //showMessage("Laptop",buffer.toString());
                    }
                }
        );
    }
    public void addingData(){
        Intent addActivity = new Intent(this, AddingData.class);
        button_add.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        startActivity(addActivity);
                    }
                }
        );
    }
    public void myLog(Cursor cursor){

        int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
        int cpuIndex = cursor.getColumnIndex(DBHelper.KEY_CPU);
        int diagonalIndex = cursor.getColumnIndex(DBHelper.KEY_DIAGONAL);
        int videoIndex = cursor.getColumnIndex(DBHelper.KEY_VIDEO_CARD);
        int volumeIndex = cursor.getColumnIndex(DBHelper.KEY_VOLUME_HD);
        int osIndex = cursor.getColumnIndex(DBHelper.KEY_OS);
        int priceIndex = cursor.getColumnIndex(KEY_PRICE);

        Log.d("mLog", "ID = " + cursor.getString(idIndex) +
                    ", cpu = " + cursor.getString(cpuIndex) +
                    ", diagonal = " + cursor.getInt(diagonalIndex) +
                    ", video card = " + cursor.getString(videoIndex) +
                    ", volume hard disk = " + cursor.getInt(volumeIndex) +
                    ", os  = " + cursor.getString(osIndex) +
                    ", price = " + cursor.getInt(priceIndex));

    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
    public void writeInFile(StringBuffer data,String fileName) throws Exception {
        FileOutputStream outputStream = openFileOutput(fileName, MODE_PRIVATE);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        bufferedWriter.write(data.toString());
        Toast.makeText(this, "Data in file", Toast.LENGTH_SHORT).show();
        bufferedWriter.close();
    }


}