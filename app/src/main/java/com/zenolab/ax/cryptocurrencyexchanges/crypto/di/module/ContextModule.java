package com.zenolab.ax.cryptocurrencyexchanges.crypto.di.module;

import android.content.Context;


import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.qualifier.ApplicationContext;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}
