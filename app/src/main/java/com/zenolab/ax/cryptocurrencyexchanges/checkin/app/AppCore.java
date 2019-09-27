package com.zenolab.ax.cryptocurrencyexchanges.checkin.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.zenolab.ax.cryptocurrencyexchanges.crypto.MyApplication;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.component.ApplicationComponent;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.component.DaggerApplicationComponent;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.module.ContextModule;

public class AppCore extends Application {

    private ComponentsHolder componentsHolder;
    //crypto
    ApplicationComponent applicationComponent;

    public static AppCore getApp(Context context) {
        return (AppCore)context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        componentsHolder = new ComponentsHolder(this);
        componentsHolder.init();

        //crypto
        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        //crypto
        applicationComponent.injectApplication(this);
    }

    public ComponentsHolder getComponentsHolder() {
        return componentsHolder;
    }

    //crypto
    public static AppCore get(Activity activity){
        return (AppCore) activity.getApplication();
    }

    //crypto
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
