package com.dtsgroup.labourlaw.application;

import android.app.Application;
import android.content.Context;

import com.dtsgroup.labourlaw.helper.LanguageHelper;
import com.dtsgroup.labourlaw.service.ConnectivityReceiver;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AppController extends Application {

    private static AppController mInstance;

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

        mInstance = this;
    }

    public static synchronized AppController getmInstance(){
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
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
