package com.example.lab3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class ActivityResult extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        imageView = (ImageView) findViewById(R.id.imageView);
        setDrawable();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setDrawable() {
        GradientDrawable shape = (GradientDrawable) getDrawable(R.drawable.circle);
        //GradientDrawable shapeStroke = (GradientDrawable) getDrawable(R.drawable.stroke);
       // GradientDrawable shapeStar = (GradientDrawable) getDrawable(R.drawable.star);
        TextView textView = findViewById(R.id.textView);
        Intent i = getIntent();
        String name = i.getStringExtra("key");
        textView.setText(name);


        switch (name) {
            case "1":
                shape.setShape(GradientDrawable.OVAL);
                shape.setColor(Color.rgb(255, 0, 0));
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
                imageView.setImageResource(R.drawable.star);
                break;
        }


    }
}