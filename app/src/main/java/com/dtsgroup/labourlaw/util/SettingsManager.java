
package com.dtsgroup.labourlaw.util;

import android.content.Context;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;

public class SettingsManager {
    public static final String FIRST_TIME_PASSED = "first_time_passed";
    public static final String KEY_LANGUAGE = "language";
    private static final String KEY_NOTIFICATIONS = "notifications";
    public static final String KEY_RESET_QUIZ = "reset_quiz";
    public static final String LANGUAGE_EN = "en";
    public static final String LANGUAGE_VI = "vi";
    public static final boolean NOTIFICATION_DISABLE = false;
    public static final boolean NOTIFICATION_ENABLED = true;
    
    public static final void setNotificationStatus(Context context, boolean p2) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("notifications", p2);
        editor.commit();
    }
    
    public static boolean getNotificationStatus(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean("notifications", true);
    }
    
    public static void setLanguage(Context context, String language) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("language", language);
        editor.commit();
    }
    
    public static String getLanguage(Context context) {
        if(context != null) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            return sharedPreferences.getString("language", "en");
        }
        return "";
    }
    
    public static boolean isVietnameseOn(Context context) {
        if(context != null) {
            return getLanguage(context).equals("vi");
        }
        return false;
    }
    
    public static void switchLanguage(Context context) {
        if(getLanguage(context).equals("en")) {
            setLanguage(context, "vi");
            return;
        }
        setLanguage(context, "en");
    }
    
    public static boolean isFirstTimePassed(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean firstTimePassed = sharedPreferences.getBoolean("first_time_passed", false);
        return firstTimePassed;
    }
    
    public static void setFirstTimePassed(Context context, boolean p2) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("first_time_passed", p2);
        editor.commit();
    }
    
    public static void setResetQuiz(Context context, boolean p2) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("reset_quiz", p2);
        editor.commit();
    }
    
    public static boolean getResetQuiz(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean reset = sharedPreferences.getBoolean("reset_quiz", false);
        return reset;
    }
}
