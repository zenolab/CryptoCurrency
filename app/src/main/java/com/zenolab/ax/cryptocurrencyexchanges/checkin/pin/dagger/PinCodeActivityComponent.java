package com.zenolab.ax.cryptocurrencyexchanges.checkin.pin.dagger;

import com.zenolab.ax.cryptocurrencyexchanges.checkin.base.dagger.ActivityComponent;
import com.zenolab.ax.cryptocurrencyexchanges.checkin.base.dagger.ActivityComponentBuilder;
import com.zenolab.ax.cryptocurrencyexchanges.checkin.pin.mvp.PinCodeActivity;

import dagger.Subcomponent;


@PinCodeActivityScope
@Subcomponent(modules = PinCodeActivityModule.class)
public interface PinCodeActivityComponent  extends ActivityComponent<PinCodeActivity> {

    @Subcomponent.Builder
    interface Builder extends ActivityComponentBuilder<PinCodeActivityComponent, PinCodeActivityModule> {

    }
}
