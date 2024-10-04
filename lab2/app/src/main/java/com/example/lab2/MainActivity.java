package com.example.lab2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.jetbrains.annotations.NonNls;

public class MainActivity extends AppCompatActivity {



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
        ImageView frog = findViewById(R.id.frog);
        registerForContextMenu(frog);

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu,menu);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Animation anim = null;
        int itemId = item.getItemId();
        ImageView frog = findViewById(R.id.frog);

        if(itemId == R.id.first_animation){
            Toast.makeText(this,"scale",Toast.LENGTH_SHORT).show();
            anim = AnimationUtils.loadAnimation(this,R.anim.scale);
        }
        else if(itemId == R.id.second_animation){
            Toast.makeText(this,"translate",Toast.LENGTH_SHORT).show();
            anim = AnimationUtils.loadAnimation(this,R.anim.trans);
        }
        else if(itemId == R.id.mixed_animation){
            Toast.makeText(this,"mixed",Toast.LENGTH_SHORT).show();
            anim = AnimationUtils.loadAnimation(this,R.anim.mixed);
        }
        frog.startAnimation(anim);
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item){


        ImageView imageView = (ImageView)findViewById(R.id.frog);

        switch (item.getItemId()) {
            case R.id.fox:
                imageView.setImageResource(R.drawable.free_png_ru_32);
                Toast.makeText(this, "it's fox", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.among:
                imageView.setImageResource(R.drawable.free_png_ru_63);
                Toast.makeText(this, "it's amongus ", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.bart:
                imageView.setImageResource(R.drawable.free_png_ru_130);
                Toast.makeText(this, "it's bart", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.frog:
                imageView.setImageResource(R.drawable.free_png_ru_207);
                Toast.makeText(this, "it's frog", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}