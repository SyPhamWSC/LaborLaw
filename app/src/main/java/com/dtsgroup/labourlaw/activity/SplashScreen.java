package com.dtsgroup.labourlaw.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dtsgroup.labourlaw.MainActivity;
import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.helper.Prefs;
import com.dtsgroup.labourlaw.helper.SharePrefUtils;
import com.dtsgroup.labourlaw.util.RealmMannager;

import io.realm.Realm;

public class SplashScreen extends AppCompatActivity {
    private static final String TAG = "SplashScreen";
    private Realm realm = Realm.getDefaultInstance();
    private int timeWait = 3000;
    private RealmMannager realmMannager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        realmMannager = new RealmMannager(this);
    }

    @Override
    protected void onStart() {
        if(!Prefs.with(SplashScreen.this).getPreLoad()){
            realmMannager.setDataRealm();
            Prefs.with(SplashScreen.this).setPreLoad(true);
//            while (!realmMannager.isHasRealm()){
//
//            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(SharePrefUtils.isFirstApp(SplashScreen.this)){
                        startApp();
                    }
                }
            },5000);

        }else {
            Thread splashThread = new Thread(){
                @Override
                public void run() {
                    try {
                        int waited = 0;
                        while(waited < timeWait) {
                            sleep(100);
                            waited += 100;
                        }
                    } catch(InterruptedException e) {
                        // do nothing
                    } finally {
                        startApp();
                    }
                }
            };
            splashThread.start();
        }

        super.onStart();
    }

    private void startApp(){
        Intent mIntent = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(mIntent);
        finish();
    }
}
