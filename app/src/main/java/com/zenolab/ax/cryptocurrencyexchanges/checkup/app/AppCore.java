package com.zenolab.ax.cryptocurrencyexchanges.checkup.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.component.CryptoRootComponent;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.component.DaggerCryptoRootComponent;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.module.ContextModule;

public class AppCore extends Application {

    /**Component dependencies*/
    CryptoRootComponent cryptoRootComponent;

    /**Subcomponents*/
    private ComponentsHolder componentsHolder;

    public static AppCore getApp(Context context) {
        return (AppCore)context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        componentsHolder = new ComponentsHolder(this);
        componentsHolder.init();

        initCryptoComponent();
    }

    public ComponentsHolder getComponentsHolder() {
        return componentsHolder;
    }

    private void initCryptoComponent() {
        cryptoRootComponent = DaggerCryptoRootComponent
                .builder()
                .contextModule(new ContextModule(this))
                .build();

        cryptoRootComponent.injectApplication(this);
    }


    public static AppCore get(Activity activity){
        return (AppCore) activity.getApplication();
    }

    public CryptoRootComponent getCryptoRootComponent() {
        return cryptoRootComponent;
    }
}
