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

        ImageView frog = findViewById(R.id.frog);
        ImageView among = findViewById(R.id.among);
        ImageView among_many = findViewById(R.id.many_among);



        Animation anim = null;
        int itemId = item.getItemId();

        if(itemId == R.id.first_animation){
            Toast.makeText(this,"complited",Toast.LENGTH_SHORT).show();
            anim = AnimationUtils.loadAnimation(this,R.anim.scale);
        }
        else if(itemId == R.id.second_animation){
            Toast.makeText(this,"complited",Toast.LENGTH_SHORT).show();
            anim = AnimationUtils.loadAnimation(this,R.anim.trans);


        }
        else if(itemId == R.id.mixed_animation){
            Toast.makeText(this,"complited",Toast.LENGTH_SHORT).show();
            anim = AnimationUtils.loadAnimation(this,R.anim.mixed);


        }
        frog.startAnimation(anim);

        return super.onOptionsItemSelected(item);
    }


    public static final int PICK_IMAGE = 99;
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item){

        int itemId = item.getItemId();

        if(itemId == R.id.frog){
            Toast.makeText(this,"complited",Toast.LENGTH_SHORT).show();


        }
        else if(itemId == R.id.among){
            Toast.makeText(this,"complited",Toast.LENGTH_SHORT).show();


        }
        else if(itemId == R.id.many_among){
            Toast.makeText(this,"complited",Toast.LENGTH_SHORT).show();

        }
        return super.onContextItemSelected(item);


    }



}