package com.zenolab.ax.cryptocurrencyexchanges.crypto;

import android.app.Activity;
import android.app.Application;

import com.zenolab.ax.cryptocurrencyexchanges.checkin.app.AppCore;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.component.ApplicationComponent;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.module.ContextModule;


public class MyApplication extends Application {

    /*
    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);

    }

    public static AppCore get(Activity activity){
        return (AppCore) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
    */
}

