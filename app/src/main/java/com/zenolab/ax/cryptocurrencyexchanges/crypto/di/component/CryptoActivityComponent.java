package com.zenolab.ax.cryptocurrencyexchanges.crypto.di.component;

import android.content.Context;


import com.zenolab.ax.cryptocurrencyexchanges.crypto.CryptoActivity;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.module.AdapterModule;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.module.CryptoActivityMvpModule;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.qualifier.ActivityContext;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.scopes.ActivityScope;

import dagger.Component;


@ActivityScope
@Component(modules = {AdapterModule.class, CryptoActivityMvpModule.class}, dependencies = ApplicationComponent.class)
public interface CryptoActivityComponent {

    @ActivityContext
    Context getContext();
    void injectCryptoActivity(CryptoActivity cryptoActivity);
}
