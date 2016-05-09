package com.csi0n.autoreceivephone;

import android.app.Application;

import com.csi0n.autoreceivephone.utils.DbManager;

/**
 * Created by csi0n on 5/8/16.
 */
public class App extends Application {
    private static App instance;
    public static App getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        initDb();
    }
    void initDb(){
        DbManager.initDb();}

}
