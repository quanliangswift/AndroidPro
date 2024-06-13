package com.example.myapplication3.common.app;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import androidx.room.Room;

import com.example.myapplication3.model.databases.AppDatabase;

public class BaseApp extends MultiDexApplication {
    private static Context mContext;
    private static Thread mThread;
    private static long mMainThreadId;
    private static Looper mMainLooper;
    private static Handler mHandler;
    private static AppDatabase appDatabase;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        mThread = Thread.currentThread();
        mMainThreadId = android.os.Process.myTid();
        mHandler = new Handler();
        appDatabase = Room.databaseBuilder(mContext, AppDatabase.class, AppDatabase.databaseName).build();
    }

    public static void restart() {
        Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        mContext.startActivity(intent);
    }

    public static Context getmContext() {
        return mContext;
    }

    public static void setmContext(Context mContext) {
        BaseApp.mContext = mContext;
    }

    public static Thread getmThread() {
        return mThread;
    }

    public static void setmThread(Thread mThread) {
        BaseApp.mThread = mThread;
    }

    public static long getmMainThreadId() {
        return mMainThreadId;
    }

    public static void setmMainThreadId(long mMainThreadId) {
        BaseApp.mMainThreadId = mMainThreadId;
    }

    public static Looper getmMainLooper() {
        return mMainLooper;
    }

    public static void setmMainLooper(Looper mMainLooper) {
        BaseApp.mMainLooper = mMainLooper;
    }

    public static Handler getmHandler() {
        return mHandler;
    }

    public static void setmHandler(Handler mHandler) {
        BaseApp.mHandler = mHandler;
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public static void setAppDatabase(AppDatabase appDatabase) {
        BaseApp.appDatabase = appDatabase;
    }
}
