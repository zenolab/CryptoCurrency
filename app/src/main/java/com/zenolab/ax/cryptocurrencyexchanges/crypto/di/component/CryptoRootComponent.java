package com.zenolab.ax.cryptocurrencyexchanges.crypto.di.component;

import android.content.Context;


import com.zenolab.ax.cryptocurrencyexchanges.checkup.app.AppCore;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.module.ContextModule;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.module.RetrofitModule;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.qualifier.ApplicationContext;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.scopes.ApplicationScope;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.data.api.APIInterface;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface CryptoRootComponent {

    //For child
    APIInterface getApiInterface();

    //For child
    @ApplicationContext
    Context getContext();

    void injectApplication(AppCore appCore);

}
