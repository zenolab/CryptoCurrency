package com.zenolab.ax.cryptocurrencyexchanges.crypto.di.module;



import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.scopes.ActivityScope;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.mvp.CryptoActivityContract;

import dagger.Module;
import dagger.Provides;

@Module
public class CryptoActivityMvpModule {
    private final CryptoActivityContract.View mView;


    public CryptoActivityMvpModule(CryptoActivityContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @ActivityScope
    CryptoActivityContract.View provideView() {
        return mView;
    }


}
