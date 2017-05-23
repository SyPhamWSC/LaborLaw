package com.dtsgroup.labourlaw.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.dtsgroup.labourlaw.common.CommonVls;

public class SharePrefUtils {

    public static void updateScore(Context context, int score){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(CommonVls.UPDATE_SCORE,score);
        editor.apply();
    }

    public static int getScore(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(CommonVls.UPDATE_SCORE,0);
    }

    public static void updateFirstApp(Context context, boolean b){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(CommonVls.FIRST_APP,b);
        editor.apply();
    }

    public static boolean isFirstApp(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(CommonVls.FIRST_APP,false);
    }
}
