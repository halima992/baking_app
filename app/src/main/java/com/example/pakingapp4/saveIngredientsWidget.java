package com.example.pakingapp4;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;

import static com.example.pakingapp4.DetilesActivity.INGREDIENT;
import static com.example.pakingapp4.DetilesActivity.SHARED_PREFS;

/**
 * Implementation of App Widget functionality.
 */
public class saveIngredientsWidget extends AppWidgetProvider {

   /* static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);
        SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String ingredient = prefs.getString(INGREDIENT,"ingredient");
        Log.d("ingredient", "onUpdate: "+ingredient);

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.save_ingredients_widget);
        views.setTextViewText(R.id.ingredient_widget, ingredient);
        //views.setTextViewText(R.id.ingredient_widget, widgetText);

        // Instruct the widget manager to update the widget
        views.setOnClickPendingIntent(R.id.ingredient_widget, pendingIntent);
        updateAppWidget(context, appWidgetManager, appWidgetId);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId,R.id.ingredient_widget);
    }*/

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                    new Intent(context, MainActivity.class), 0);
            SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            String ingredient = prefs.getString(INGREDIENT,"ingredient");
            Log.d("ingredient", "onUpdate: "+ingredient);

            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.save_ingredients_widget);
            views.setTextViewText(R.id.ingredient_widget, ingredient);
            //views.setTextViewText(R.id.ingredient_widget, widgetText);

            // Instruct the widget manager to update the widget
            views.setOnClickPendingIntent(R.id.ingredient_widget, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId,R.id.ingredient_widget);

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

