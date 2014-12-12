package com.example.walaoeh;

import android.app.Application;
import com.example.walaoeh.helper.Pref;

/**
 * Created by taingmeng on 15/11/14.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Pref.init(getApplicationContext());

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
