package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity  {

    String num = "0";
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
    }

    public void onClickedButton(View view) {
        Intent intent = new Intent(this, ActivityResult.class);

        RadioGroup radioGroup = findViewById(R.id.group);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        //num = "Oval";
        switch (selectedId){
            case R.id.oval:
                num = "1";
                break;
            case R.id.rectangle:
                num = "2";
                break;
            case R.id.hexagon:
                num = "3";
                break;
            case R.id.traingle:
                num = "4";
                break;

        }

        Button buttonResult;

        switch (view.getId()){
            case R.id.buttonResult:
                intent.putExtra("key",num);
                startActivity(intent);
                break;
            default:
                break;
        }

    }

}