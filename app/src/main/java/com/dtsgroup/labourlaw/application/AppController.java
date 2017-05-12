package com.dtsgroup.labourlaw.application;

import android.app.Application;
import android.content.Context;

import com.dtsgroup.labourlaw.helper.LanguageHelper;

public class AppController extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LanguageHelper.onAttach(base, "vi"));
    }

}
