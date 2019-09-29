package com.zenolab.ax.cryptocurrencyexchanges.checkin.pin.dagger;

import com.zenolab.ax.cryptocurrencyexchanges.checkin.base.dagger.ActivityModule;
import com.zenolab.ax.cryptocurrencyexchanges.checkin.common.Constants;
import com.zenolab.ax.cryptocurrencyexchanges.checkin.pin.mvp.PinCodeChangePresenter;
import com.zenolab.ax.cryptocurrencyexchanges.checkin.pin.mvp.PinCodeCheckPresenter;
import com.zenolab.ax.cryptocurrencyexchanges.checkin.pin.mvp.PinCodeContract;
import com.zenolab.ax.cryptocurrencyexchanges.checkin.pin.mvp.PinCodeCreatePresenter;
import com.zenolab.ax.cryptocurrencyexchanges.checkin.storage.Preferences;

import dagger.Module;
import dagger.Provides;


@Module
public class PinCodeActivityModule implements ActivityModule {

    private final Constants.PinCodeMode pinCodeMode;

    public PinCodeActivityModule(Constants.PinCodeMode pinCodeMode) {
        this.pinCodeMode = pinCodeMode;
    }

    @PinCodeActivityScope
    @Provides
    PinCodeContract.Presenter providePinCodePresenter(Preferences preferences) {
        switch (pinCodeMode) {
            case CREATE:
                return new PinCodeCreatePresenter(preferences);
            case CHANGE:
                return new PinCodeChangePresenter(preferences);
            case CHECK:
                return new PinCodeCheckPresenter(preferences);
            default:
                throw new RuntimeException("No matching presenter found "
                                           + "to create an encapsulated object"
                                           + " by Dagger2 provider ");
        }
    }
}
