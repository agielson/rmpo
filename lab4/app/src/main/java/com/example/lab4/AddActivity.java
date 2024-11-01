package com.example.lab4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {

    EditText editText_price,editText_video_card,editText_diagonal,editText_CPU,editText_os,editText_volume_hd;
    Button add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editText_CPU = findViewById(R.id.editText_CPU);
        editText_diagonal = findViewById(R.id.editText_diagonal);
        editText_video_card = findViewById(R.id.editText_video_card);
        editText_os = findViewById(R.id.editText_os);
        editText_price = findViewById(R.id.editText_price);
        editText_volume_hd = findViewById(R.id.editText_volume_hd);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDataBase myBD = new MyDataBase(AddActivity.this);
                myBD.addLaptop(editText_CPU.getText().toString().trim(),
                        Integer.valueOf(editText_diagonal.getText().toString().trim()),
                        editText_video_card.getText().toString().trim(),
                        Integer.valueOf(editText_volume_hd.getText().toString().trim()),
                        editText_os.getText().toString().trim(),
                        Integer.valueOf(editText_price.getText().toString().trim()));
            }
        });
    }
}