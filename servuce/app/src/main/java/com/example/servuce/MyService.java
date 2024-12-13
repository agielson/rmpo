package com.example.servuce;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;

public class MyService extends Service {
    private Handler handler;
    private Runnable runnable;
    private int originalBrightness;


    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int duration = intent.getIntExtra("duration", 0);
        originalBrightness = getCurrentBrightness();

        runnable = new Runnable() {
            int count = 0;

            @Override
            public void run() {
                if (count < duration) {
                    int newBrightness = count % 2 == 0 ? originalBrightness - 50 : originalBrightness + 50;
                    if (newBrightness < 0) newBrightness = 0;
                    if (newBrightness > 255) newBrightness = 255;
                    setBrightness(newBrightness);
                    count++;
                    handler.postDelayed(this, 5000);
                } else {
                    setBrightness(originalBrightness); // Возврат к оригинальной яркости
                    stopSelf();
                }
            }
        };

        handler.post(runnable);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        setBrightness(originalBrightness);
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

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}