package com.example.lab32;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.BreakIterator;

public class SelectFigure extends AppCompatActivity {

    public static final String ShapeExtras = "shape_extras";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_figure);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void buttonClick(View view){
        Intent returnBtn = getIntent();
        RadioGroup radioGroup = findViewById(R.id.group);
        String shape = null;
        int selectedId = radioGroup.getCheckedRadioButtonId();
        switch (selectedId){
            case R.id.oval:
                shape = "1";
                break;
            case R.id.rectangle:
                shape = "2";
                break;
            case R.id.egg:
                shape = "3";
                break;
            case R.id.ring:
                shape = "4";
                break;

        }
        returnBtn.putExtra(ShapeExtras, shape);
        setResult(RESULT_OK,returnBtn);
        finish();

    }
}