package com.kartikbhargav.bakingapp;

import android.app.Application;

/**
 * Created by kbhargav on 3/25/2018.
 */

public class App extends Application {

    static App context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = (App) getApplicationContext();
    }

    public static App getContext() {
        return context;
    }
}


