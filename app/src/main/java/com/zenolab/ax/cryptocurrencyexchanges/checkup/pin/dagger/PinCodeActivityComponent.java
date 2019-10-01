package com.zenolab.ax.cryptocurrencyexchanges.checkup.pin.dagger;

import com.zenolab.ax.cryptocurrencyexchanges.checkup.base.dagger.ActivityComponent;
import com.zenolab.ax.cryptocurrencyexchanges.checkup.base.dagger.ActivityComponentBuilder;
import com.zenolab.ax.cryptocurrencyexchanges.checkup.pin.mvp.PinCodeActivity;

import dagger.Subcomponent;


@PinCodeActivityScope
@Subcomponent(modules = PinCodeActivityModule.class)
public interface PinCodeActivityComponent  extends ActivityComponent<PinCodeActivity> {

    @Subcomponent.Builder
    interface Builder extends ActivityComponentBuilder<PinCodeActivityComponent, PinCodeActivityModule> {

    }
}
