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

    public static void savePlayerState(int stage, int level){
        editor.putInt(Const.STAGE_KEY, stage);
        editor.putInt(Const.LEVEL_KEY, level);
        editor.commit();
    }




}