package com.dtsgroup.labourlaw.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dtsgroup.labourlaw.MainActivity;
import com.dtsgroup.labourlaw.R;
import com.dtsgroup.labourlaw.application.AppController;
import com.dtsgroup.labourlaw.helper.Prefs;
import com.dtsgroup.labourlaw.helper.SharePrefUtils;
import com.dtsgroup.labourlaw.service.ConnectivityReceiver;
import com.dtsgroup.labourlaw.util.RealmMannager;

import io.realm.Realm;

public class SplashScreen extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{
    private static final String TAG = "SplashScreen";
    private static final int RELOAD_DB = 1;
    private static final int EXIT_APP = 2;

    private Realm realm = Realm.getDefaultInstance();
    private int timeWait = 3000;
    private RealmMannager realmMannager;
    private FrameLayout frameLayout;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        frameLayout = (FrameLayout) findViewById(R.id.fr_layout);
        realmMannager = new RealmMannager(this);
        checkConnection();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                switch (msg.what){
                    case RELOAD_DB:
                        showSnack(ConnectivityReceiver.isConnected());
                        realmMannager.setDataRealm();
                        Prefs.with(SplashScreen.this).setPreLoad(true);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(SharePrefUtils.isFirstApp(SplashScreen.this)){
                                    startApp();
                                }
                            }
                        },8000);
                        break;
                    case EXIT_APP:
                        System.exit(0);
                }
            }
        };
    }

    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
        loadDB();
    }

    private void loadDB() {
        if(!Prefs.with(SplashScreen.this).getPreLoad()){
            boolean isConnected = ConnectivityReceiver.isConnected();
            if(!isConnected){
                showSnack(isConnected);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int mili = 1000;
                        while (!ConnectivityReceiver.isConnected() && mili > 0){
                            mili -= 1;
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if(mili>0){
                            Message msg = new Message();
                            msg.what = RELOAD_DB;
                            msg.setTarget(handler);
                            msg.sendToTarget();
                        }else {
                            Message msg = new Message();
                            msg.what = EXIT_APP;
                            msg.setTarget(handler);
                            msg.sendToTarget();
                        }

                    }
                }).start();
            }else {
                showSnack(isConnected);
                realmMannager.setDataRealm();
                Prefs.with(SplashScreen.this).setPreLoad(true);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(SharePrefUtils.isFirstApp(SplashScreen.this)){
                            startApp();
                        }
                    }
                },5000);
            }


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

    }

//    @Override
//    protected void onStart() {
//        if(!Prefs.with(SplashScreen.this).getPreLoad()){
//            boolean isConnected = ConnectivityReceiver.isConnected();
//            if(!isConnected){
//                showSnack(isConnected);
//            }else {
//                realmMannager.setDataRealm();
//                Prefs.with(SplashScreen.this).setPreLoad(true);
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        if(SharePrefUtils.isFirstApp(SplashScreen.this)){
//                            startApp();
//                        }
//                    }
//                },5000);
//            }
//
//
//        }else {
//            Thread splashThread = new Thread(){
//                @Override
//                public void run() {
//                    try {
//                        int waited = 0;
//                        while(waited < timeWait) {
//                            sleep(100);
//                            waited += 100;
//                        }
//                    } catch(InterruptedException e) {
//                        // do nothing
//                    } finally {
//                        startApp();
//                    }
//                }
//            };
//            splashThread.start();
//        }
//
//        super.onStart();
//    }

    private void startApp(){
        Intent mIntent = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(mIntent);
        finish();
    }

    private void showSnack(boolean isConnected){
        String message;
        int color;
        if (isConnected) {
            message = "Good! Connected to Internet";
            color = Color.WHITE;
        } else {
            message = "Sorry! Not connected to internet";
            color = Color.RED;
        }

        Snackbar snackbar = Snackbar
                .make(frameLayout, message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }
    @Override
    protected void onResume() {
        super.onResume();

        AppController.getmInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        loadDB();
    }


}
