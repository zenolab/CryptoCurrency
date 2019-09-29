package com.zenolab.ax.cryptocurrencyexchanges.checkin.app.dagger;

import com.zenolab.ax.cryptocurrencyexchanges.checkin.base.dagger.ActivityComponentBuilder;
import com.zenolab.ax.cryptocurrencyexchanges.checkin.pin.dagger.PinCodeActivityComponent;
import com.zenolab.ax.cryptocurrencyexchanges.checkin.pin.mvp.PinCodeActivity;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;


@Module(subcomponents = {PinCodeActivityComponent.class})
public class AppSubComponentsModule {

    @Provides
    @IntoMap
    @ClassKey(PinCodeActivity.class)
    ActivityComponentBuilder provideSplashViewBuilder(PinCodeActivityComponent.Builder builder) {
        return builder;
    }

}
