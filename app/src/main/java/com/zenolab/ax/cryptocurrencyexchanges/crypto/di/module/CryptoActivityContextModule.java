package com.zenolab.ax.cryptocurrencyexchanges.crypto.di.module;

import android.content.Context;


import com.zenolab.ax.cryptocurrencyexchanges.crypto.presentation.ui.CryptoActivity;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.qualifier.ActivityContext;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class CryptoActivityContextModule {
    private CryptoActivity cryptoActivity;

    public Context context;

    public CryptoActivityContextModule(CryptoActivity cryptoActivity) {
        this.cryptoActivity = cryptoActivity;
        context = cryptoActivity;
    }

    @Provides
    @ActivityScope
    public CryptoActivity providesMainActivity() {
        return cryptoActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }

}
