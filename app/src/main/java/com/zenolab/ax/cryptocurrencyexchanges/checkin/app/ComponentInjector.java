package com.zenolab.ax.cryptocurrencyexchanges.checkin.app;

import android.app.Activity;
import android.content.Context;

import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.component.CryptoRootComponent;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.component.DaggerCryptoRootComponent;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.module.ContextModule;

public class ComponentInjector {

    private static Context context;
    private static ComponentInjector instance;
    /**Component dependencies*/
    CryptoRootComponent cryptoRootComponent;

    private ComponentInjector() {
        initCryptoComponent();
    }

    public static void initInstance(Context context) {
        if (instance == null) {
            instance = new ComponentInjector();
            ComponentInjector.context = context;
        }
    }

    public static ComponentInjector getInstance() {
        return instance;
    }

    private void initCryptoComponent() {
        cryptoRootComponent = DaggerCryptoRootComponent
                .builder()
                .contextModule(new ContextModule(context))
                .build();

        cryptoRootComponent.injectComponentInjector(instance);
    }


    public static AppCore get(Activity activity){
        return (AppCore) activity.getApplication();
    }

    public CryptoRootComponent getCryptoRootComponent() {
        return cryptoRootComponent;
    }
}
