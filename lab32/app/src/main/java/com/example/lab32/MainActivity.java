package com.example.lab32;
// SelectFigure.java
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    ImageView imageView;




    private class ActivityResultFabric
    {
        public ActivityResultLauncher<Intent> create(ActivityResultCallback<ActivityResult> callback)
        {
            return registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), callback);
        }
    }

    private ActivityResultLauncher<Intent> figureResultLauncher;
    private ActivityResultLauncher<Intent> colorResultLauncher;
    private ActivityResultLauncher<Intent> positionResultLauncher;



    public void setDrawable(String name){

        GradientDrawable shape = (GradientDrawable) getDrawable(R.drawable.circle);
        GradientDrawable shapeStar = (GradientDrawable) getDrawable(R.drawable.star);
        GradientDrawable shapeStroke = (GradientDrawable) getDrawable(R.drawable.stroke);

        switch (name) {
            case "1":
                shape.setShape(GradientDrawable.OVAL);
                imageView.setImageResource(R.drawable.circle);
                break;
            case "2":
                shape.setShape(GradientDrawable.RECTANGLE);
                imageView.setImageResource(R.drawable.circle);
                break;
            case "3":
                imageView.setImageResource(R.drawable.stroke);
                break;
            case "4":
                shapeStar.setShape(GradientDrawable.RING);
                imageView.setImageResource(R.drawable.star);
                break;
        }

    }
    public void setColor(String colorString){
        GradientDrawable shape = (GradientDrawable) getDrawable(R.drawable.circle);
        GradientDrawable shapeStar = (GradientDrawable) getDrawable(R.drawable.star);
        GradientDrawable shapeStroke = (GradientDrawable) getDrawable(R.drawable.stroke);
        int color = Color.parseColor("#"+colorString);
        shape.setColor(color);
        shape.setColor(color);
        shapeStroke.setColor(color);
        shapeStar.setColor(color);

    }
    public void setPosition(int x, int y){

        imageView.setTranslationX(x);
        imageView.setTranslationY(y);
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityResultFabric fabric = new ActivityResultFabric();
        figureResultLauncher = fabric.create(new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                    if (intent == null) return;

                    String shape = intent.getStringExtra(SelectFigure.ShapeExtras);
                    if (shape != null)
                        setDrawable(shape);
                }
            }
        });

        colorResultLauncher = fabric.create(new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                    if (intent == null) return;

                    String color = intent.getStringExtra(InputColor.ColorExtras);
                    if(color != null)
                    {
                        setColor(color);
                    }
                }
            }
        });
        positionResultLauncher = fabric.create(new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                    if (intent == null) return;
                    int positionX = intent.getIntExtra(SetPosition.PositionExtrasX,0);
                    int positionY = intent.getIntExtra(SetPosition.PositionExtrasY,0);

                    if(positionX != 0 && positionY !=0)
                    {
                        setPosition(positionX,positionY);
                    }
                }
            }
        });

        Button chooseShapeButton = findViewById(R.id.setFigure);
        Button enterTextButton = findViewById(R.id.setColor);
        Button enterPositionButton = findViewById(R.id.setPosition);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.circle);




        chooseShapeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.myapp.SEND_DATA");
                figureResultLauncher.launch(intent);
            }
        });

        enterTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.myapp.COLOR");
                colorResultLauncher.launch(intent);
            }
        });
        enterPositionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.myapp.POSITION");
                positionResultLauncher.launch(intent);
            }
        });

    }
}
