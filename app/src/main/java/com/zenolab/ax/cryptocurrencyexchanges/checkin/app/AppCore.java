package com.zenolab.ax.cryptocurrencyexchanges.checkin.app;

import android.app.Application;
import android.content.Context;

public class AppCore extends Application {

    private ComponentsHolder componentsHolder;

    public static AppCore getApp(Context context) {
        return (AppCore)context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        componentsHolder = new ComponentsHolder(this);
        componentsHolder.init();
    }

    public ComponentsHolder getComponentsHolder() {
        return componentsHolder;
    }
}
