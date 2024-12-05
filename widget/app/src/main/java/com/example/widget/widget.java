package com.example.widget;

import static android.content.Context.MODE_PRIVATE;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link widgetConfigureActivity widgetConfigureActivity}
 */
public class widget extends AppWidgetProvider {
    public static String YOUR_AWESOME_ACTION = "YourAwesomeAction";
    public static String widgetId = "widgetId";


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = getFormattedDate(context);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        //Ставим цвет
        //views.setInt(R.id.LineaRLayout1, "setBackgroundColor", Color.BLUE);

        SharedPreferences sp = context.getSharedPreferences(widgetConfigureActivity.PREFS_NAME,MODE_PRIVATE);

        String color = sp.getString( widgetConfigureActivity.PREF_COLOR+String.valueOf(appWidgetId),"#ffffff");

        views.setInt(R.id.LineaRLayout1, "setBackgroundColor", Color.parseColor(color));

        Intent confIntent = new Intent(context, widgetConfigureActivity.class);
        confIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,appWidgetId);

        confIntent.setData(Uri.parse(confIntent.toUri(Intent.URI_INTENT_SCHEME)));
        views.setOnClickPendingIntent(R.id.appwidget_text, PendingIntent.getActivity(context,appWidgetId,confIntent,
                PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE));

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }



    private static CharSequence getFormattedDate(Context context){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd ,MMMM", new Locale("ru"));
        return dateFormat.format(calendar.getTime());
    }


    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            //widgetConfigureActivity.deleteTitlePref(context, appWidgetId);
            Intent intent = new Intent(context, widget.class);
            intent.setAction(YOUR_AWESOME_ACTION);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}