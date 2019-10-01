package com.zenolab.ax.cryptocurrencyexchanges.crypto.di.module;

import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.scopes.ActivityScope;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.presentation.mvp.CryptoActivityContract;

import dagger.Module;
import dagger.Provides;

@Module
public class CryptoActivityMvpModule {
    private final CryptoActivityContract.View view;

    public CryptoActivityMvpModule(CryptoActivityContract.View view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    CryptoActivityContract.View provideView() {
        return view;
    }

}
