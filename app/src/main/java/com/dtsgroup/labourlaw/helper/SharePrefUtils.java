package com.dtsgroup.labourlaw.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.dtsgroup.labourlaw.common.CommonVls;

/**
 * Created by vantr on 5/18/2017.
 */

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
}
