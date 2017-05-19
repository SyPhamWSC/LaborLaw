package com.dtsgroup.labourlaw.application;

import android.app.Application;
import android.content.Context;

import com.dtsgroup.labourlaw.helper.LanguageHelper;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AppController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("EHS_Labor_Law")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LanguageHelper.onAttach(base, "vi"));
    }

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }
}
