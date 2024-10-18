package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


        EditText editText;
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
            case R.id.egg:
                num = "3";
                break;
            case R.id.ring:
                num = "4";
                break;

        }

        Button buttonResult;
        editText = (EditText) findViewById(R.id.color);
        intent.putExtra("color",editText.getText().toString().toUpperCase());

        int zero = 1;
        int centerX = 159;
        int centerY = 250;


        switch (view.getId()){
            case R.id.buttonResult1:
                intent.putExtra("key",num);
                intent.putExtra("x",zero);
                intent.putExtra("y",zero);
                startActivity(intent);
                break;
            case R.id.buttonResult2:
                intent.putExtra("key",num);
                intent.putExtra("x",centerX);
                intent.putExtra("y",zero);
                startActivity(intent);
                break;
            case R.id.buttonResult3:
                intent.putExtra("key",num);
                intent.putExtra("x",centerX*2);
                intent.putExtra("y",zero);
                startActivity(intent);
                break;
            case R.id.buttonResult4:
                intent.putExtra("key",num);
                intent.putExtra("x",zero);
                intent.putExtra("y",centerY);
                startActivity(intent);
                break;
            case R.id.buttonResult5:
                intent.putExtra("key",num);
                intent.putExtra("x",centerX);
                intent.putExtra("y",centerY);
                startActivity(intent);
                break;
            case R.id.buttonResult6:
                intent.putExtra("key",num);
                intent.putExtra("x",centerX*2);
                intent.putExtra("y",centerY);
                startActivity(intent);
                break;
            case R.id.buttonResult7:
                intent.putExtra("key",num);
                intent.putExtra("x",zero);
                intent.putExtra("y",centerY*2);
                startActivity(intent);
                break;
            case R.id.buttonResult8:
                intent.putExtra("key",num);
                intent.putExtra("x",centerX);
                intent.putExtra("y",centerY*2);
                startActivity(intent);
                break;
            case R.id.buttonResult9:
                intent.putExtra("key",num);
                intent.putExtra("x",centerX*2);
                intent.putExtra("y",centerY*2);
                startActivity(intent);
                break;



            default:
                break;
        }


    }

}