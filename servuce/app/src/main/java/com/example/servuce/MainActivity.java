package com.example.servuce;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private SeekBar brightnessSeekBar;
    private Button startTimerButton;
    private EditText timerEditText;
    private Button stopServiceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        brightnessSeekBar = findViewById(R.id.brightnessSeekBar);
        startTimerButton = findViewById(R.id.startTimerButton);
        timerEditText = findViewById(R.id.timerEditText);
        stopServiceButton = findViewById(R.id.stopServiceButton);

        // Установка текущей яркости при запуске
        int currentBrightness = getCurrentBrightness();
        brightnessSeekBar.setProgress(currentBrightness);

        // Изменение яркости экрана при изменении SeekBar
        brightnessSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setBrightness(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Обработка нажатия на кнопку установки таймера
        startTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int timerDuration;
                try {
                    timerDuration = Integer.parseInt(timerEditText.getText().toString());
                    if (timerDuration > 0) {
                        startBrightnessService(timerDuration);
                    } else {
                        Toast.makeText(MainActivity.this, "Введите время больше 0", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Введите корректное значение времени", Toast.LENGTH_SHORT).show();
                }
            }
        });
        stopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, MyService.class));
                Toast.makeText(MainActivity.this, "Сервис остановлен", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private int getCurrentBrightness() {
        int brightness = 0;
        try {
            brightness = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return brightness;
    }


    private void setBrightness(int brightness) {
        ContentResolver cResolver = getContentResolver();
        Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, brightness);
    }

    private void startBrightnessService(int duration) {
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("duration", duration);
        startService(intent);
    }
}