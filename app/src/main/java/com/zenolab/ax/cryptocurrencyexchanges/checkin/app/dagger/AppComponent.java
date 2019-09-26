package com.zenolab.ax.cryptocurrencyexchanges.checkin.app.dagger;

import com.zenolab.ax.cryptocurrencyexchanges.checkin.app.ComponentsHolder;
import com.zenolab.ax.cryptocurrencyexchanges.checkin.storage.Preferences;

import dagger.Component;


@AppScope
@Component(modules = {AppModule.class, AppSubComponentsModule.class})
public interface AppComponent {
    void injectComponentsHolder(ComponentsHolder componentsHolder);
    Preferences getPreferences();
}
