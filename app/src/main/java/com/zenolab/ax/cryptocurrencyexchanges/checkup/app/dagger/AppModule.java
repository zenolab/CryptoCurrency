package com.zenolab.ax.cryptocurrencyexchanges.checkup.app.dagger;

import android.content.Context;

import com.zenolab.ax.cryptocurrencyexchanges.checkup.storage.Preferences;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @AppScope
    @Provides
    Context provideContext() {
        return context;
    }

    @AppScope
    @Provides
    Preferences providePreferences(Context context) {
        return new Preferences(context);
    }


}
