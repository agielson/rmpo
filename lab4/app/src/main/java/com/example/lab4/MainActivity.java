package com.example.lab4;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private CustomAdapter customAdapter;
    private MyDataBase myDB;
    private ArrayList<String> id, name_of_cpu, diagonal,video_card, volume_hd,os,price;
    RecyclerView recyclerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        }
        );

        FloatingActionButton add_button = findViewById(R.id.add_button);
        recyclerView = findViewById(R.id.recyclerView);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this ,AddActivity.class);
                startActivity(intent);

            }
        });

        myDB = new MyDataBase(MainActivity.this);
        id = new ArrayList<>();
        name_of_cpu = new ArrayList<>();
        diagonal = new ArrayList<>();
        video_card = new ArrayList<>();
        volume_hd = new ArrayList<>();
        os = new ArrayList<>();
        price = new ArrayList<>();

        storeDataInArray();
        customAdapter = new CustomAdapter(MainActivity.this, id, name_of_cpu, diagonal,video_card, volume_hd,os,price);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        recyclerView.setAdapter(customAdapter);

    }
    void storeDataInArray(){

        Cursor cursor = myDB.readAllData();
        if (cursor == null) Log.d("CURSOR", "cursor empty");
        if(cursor.getCount() == 0){
            if (cursor == null) Log.d("CURSOR", "no data");

            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }else
        {
            while(cursor.moveToNext()){
                int idColIndex = cursor.getColumnIndex(MyDataBase.COLUMN_ID);
                int cpuNameColIndex = cursor.getColumnIndex(MyDataBase.COLUMN_NAME_OF_CPU);
                int diagonalColIndex = cursor.getColumnIndex(MyDataBase.COLUMN_DIAGONAL);
                int videoCardColIndex = cursor.getColumnIndex(MyDataBase.COLUMN_VIDEO_CARD);
                int volHDColIndex = cursor.getColumnIndex(MyDataBase.COLUMN_VOLUME_OF_HARD_DATA);
                int osColIndex = cursor.getColumnIndex(MyDataBase.COLUMN_OS);
                int priceColIndex = cursor.getColumnIndex(MyDataBase.COLUMN_PRICE);

                if (idColIndex != -1) id.add(cursor.getString(idColIndex));
                if (cpuNameColIndex != -1) name_of_cpu.add(cursor.getString(cpuNameColIndex));
                if (diagonalColIndex != -1) diagonal.add(cursor.getString(diagonalColIndex));
                if (videoCardColIndex != -1) video_card.add(cursor.getString(videoCardColIndex));
                if (volHDColIndex != -1) volume_hd.add(cursor.getString(volHDColIndex));
                if (osColIndex != -1) os.add(cursor.getString(osColIndex));
                if (priceColIndex != -1) price.add(cursor.getString(priceColIndex));

            }
            //customAdapter.notifyDataSetChanged();
        }

    }
}