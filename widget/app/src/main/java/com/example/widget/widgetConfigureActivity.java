package com.example.widget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.widget.databinding.WidgetConfigureBinding;

/**
 * The configuration screen for the {@link widget widget} AppWidget.
 */
public class widgetConfigureActivity extends Activity {
    ImageButton blue,red,per,green;

    public static final String PREFS_NAME = "com.example.widget.widget";
    private static final String PREF_PREFIX_KEY = "appwidget_";
    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    EditText mAppWidgetText;

    public static final String PREF_COLOR = "color_";

    private WidgetConfigureBinding binding;

    public widgetConfigureActivity() {
        super();
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setResult(RESULT_CANCELED);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }

        binding = WidgetConfigureBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAppWidgetText = binding.appwidgetText;
        binding.imageButtonBlue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Context context = widgetConfigureActivity.this;
                String color ="#1403fc";
                SharedPreferences sp = getSharedPreferences(PREF_COLOR,MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                switch(v.getId()){
                    case R.id.imageButtonBlue:
                        color = "#1403fc";
                        break;
                    case R.id.imageButtonGreen:
                        color = "#eb03fc";
                        break;
                    case R.id.imageButtonRed:
                        color = "#fc5603";
                        break;
                    case R.id.imageButtonPer:
                        color = "#14fc03";
                        break;
                    default:
                        break;

                }

                //editor.putString(PREF_COLOR+String.valueOf(widget.widgetId),color);
                //editor.putString(PREF_FONT+String.valueOf(widget.widgetId),color);//lytresrtyuiop[]

                editor.putString("color","#14fc03");


                editor.commit();

                // It is the responsibility of the configuration activity to update the app widget
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                widget.updateAppWidget(context, appWidgetManager, mAppWidgetId);

                Intent resultValue = new Intent();
                resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
                setResult(RESULT_OK, resultValue);
                finish();
            }
        });


    }
}