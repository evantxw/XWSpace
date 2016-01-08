package com.evan.xwspace.app;

import android.app.Application;

import org.xutils.x;

/**
 * Created by evan on 2016/1/4.
 */
public class TApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
