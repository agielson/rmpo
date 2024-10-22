package com.example.lab32;

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

public class SetPosition extends AppCompatActivity {

    public static final String PositionExtrasX = "position_extras_X";
    public static final String PositionExtrasY = "position_extras_Y";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_set_position);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void onClickedButton(View view) {
        Intent returnBtn = getIntent();


        int zero = 1;
        int centerX = 159;
        int centerY = 250;


        switch (view.getId()){
            case R.id.buttonResult1:
                returnBtn.putExtra(PositionExtrasX, zero);
                returnBtn.putExtra(PositionExtrasY, zero);
                break;
            case R.id.buttonResult2:
                returnBtn.putExtra(PositionExtrasX, centerX);
                returnBtn.putExtra(PositionExtrasY, zero);
                break;
            case R.id.buttonResult3:
                returnBtn.putExtra(PositionExtrasX, centerX*2);
                returnBtn.putExtra(PositionExtrasY, zero);
                break;
            case R.id.buttonResult4:
                returnBtn.putExtra(PositionExtrasX, zero);
                returnBtn.putExtra(PositionExtrasY, centerY);
                break;
            case R.id.buttonResult5:
                returnBtn.putExtra(PositionExtrasX, centerX);
                returnBtn.putExtra(PositionExtrasY, centerY);

                break;
            case R.id.buttonResult6:
                returnBtn.putExtra(PositionExtrasX, centerX*2);
                returnBtn.putExtra(PositionExtrasY, centerY);
                break;
            case R.id.buttonResult7:
                returnBtn.putExtra(PositionExtrasX, zero);
                returnBtn.putExtra(PositionExtrasY, centerY*2);
                break;
            case R.id.buttonResult8:
                returnBtn.putExtra(PositionExtrasX, centerX);
                returnBtn.putExtra(PositionExtrasY, centerY*2);
                break;
            case R.id.buttonResult9:

                returnBtn.putExtra(PositionExtrasX, centerX*2);
                returnBtn.putExtra(PositionExtrasY, centerY*2);
                break;

        }
        setResult(RESULT_OK,returnBtn);
        finish();


    }
}