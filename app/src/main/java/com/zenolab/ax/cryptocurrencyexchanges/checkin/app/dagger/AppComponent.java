package com.zenolab.ax.cryptocurrencyexchanges.checkin.app.dagger;

import com.zenolab.ax.cryptocurrencyexchanges.checkin.app.ComponentsHolder;
import com.zenolab.ax.cryptocurrencyexchanges.checkin.storage.Preferences;

import dagger.Component;


@AppScope
@Component(modules = {AppModule.class, AppSubComponentsModule.class})
public interface AppComponent {
    Preferences getPreferences();
    void injectComponentsHolder(ComponentsHolder componentsHolder);
}
