package com.example.walaoeh.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by taingmeng on 5/11/14.
 */
public class Pref {
    private static SharedPreferences prefs;
    private static SharedPreferences.Editor editor;
    private static Pref instance;

    public static void init(Context context){
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        editor = prefs.edit();
    }

    public static int getPlayerState(String key){
        return prefs.getInt(key, 0);
    }

    public static int getPlayerStage() {
        return prefs.getInt(Const.STAGE_KEY, 0);
    }

    public static int getPlayerLevel() {
        return prefs.getInt(Const.LEVEL_KEY, 0);
    }

    public static int getPlayerFirstTime() {return prefs.getInt(Const.FIRST_TIME_KEY, 0);}

    public static boolean getStopTimerState() {
        return prefs.getBoolean(Const.STOP_TIMER_STATE, false);
    }

    public static void savePlayerState(int stage, int level){
        editor.putInt(Const.STAGE_KEY, stage);
        editor.putInt(Const.LEVEL_KEY, level);
        editor.commit();
    }

    public static void savePlayerStage(int stage) {
        editor.putInt(Const.STAGE_KEY, stage);
        editor.commit();
    }

    public static void savePlayerLevel(int level) {
        editor.putInt(Const.LEVEL_KEY, level);
        editor.commit();
    }

    public static void saveStopTimerState(boolean stopTimer) {
        editor.putBoolean(Const.STOP_TIMER_STATE, stopTimer);
        editor.commit();
    }

    public static void saveFirstTime(int visited) {
        editor.putInt(Const.FIRST_TIME_KEY, visited);
        editor.commit();
    }


}
