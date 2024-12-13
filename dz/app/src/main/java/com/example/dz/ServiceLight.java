package com.example.dz;

package com.example.dz;

import android.app.Service;
import android.content.Intent;
import android.content.ContentResolver;
import android.provider.Settings;
import android.os.IBinder;
import android.os.Handler;

public class ServiceLight extends Service {

    private Handler handler = new Handler();
    private int brightnessLevel = 255; // Максимальная яркость
    private boolean isServiceRunning = false;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isServiceRunning = true;
        adjustBrightness(brightnessLevel);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        isServiceRunning = false;
        resetBrightness();
        super.onDestroy();
    }

    public void setBrightness(int brightness) {
        this.brightnessLevel = brightness;
        if (isServiceRunning) {
            adjustBrightness(brightness);
        }
    }

    private void adjustBrightness(int brightness) {
        ContentResolver cResolver = getContentResolver();
        Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, brightness);
    }

    private void resetBrightness() {
        // Устанавливаем более безопасный уровень яркости при завершении
        adjustBrightness(128);
    }
}