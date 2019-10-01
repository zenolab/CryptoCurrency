package com.zenolab.ax.cryptocurrencyexchanges.checkup.app.dagger;

import com.zenolab.ax.cryptocurrencyexchanges.checkup.app.ComponentsHolder;
import com.zenolab.ax.cryptocurrencyexchanges.checkup.storage.Preferences;

import dagger.Component;


@AppScope
@Component(modules = {AppModule.class, AppSubComponentsModule.class})
public interface AppComponent {
    Preferences getPreferences();
    void injectComponentsHolder(ComponentsHolder componentsHolder);
}
