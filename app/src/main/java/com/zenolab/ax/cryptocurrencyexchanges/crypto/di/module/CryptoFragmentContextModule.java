package com.zenolab.ax.cryptocurrencyexchanges.crypto.di.module;

import android.content.Context;

import com.zenolab.ax.cryptocurrencyexchanges.crypto.CryptoActivity;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.CryptoFragment;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.qualifier.ActivityContext;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class CryptoFragmentContextModule {

    private CryptoFragment cryptoFragment;

    public Context context;

    public CryptoFragmentContextModule(CryptoFragment cryptoFragment ,Context context) {
        this.cryptoFragment = cryptoFragment;
        this.context =  context;
    }

    public CryptoFragmentContextModule(CryptoFragment cryptoFragment) {
        this.cryptoFragment = cryptoFragment;
        this.context =  context;
    }

    @Provides
    @ActivityScope
    public CryptoFragment providesCryptoFragment() {
        return cryptoFragment;
    }

//    @Provides
//    @ActivityScope
//    @ActivityContext
//    public Context provideContext() {
//        return context;
//    }



}
