package com.zenolab.ax.cryptocurrencyexchanges.checkup.pin.dagger;

import com.zenolab.ax.cryptocurrencyexchanges.checkup.base.dagger.ActivityModule;
import com.zenolab.ax.cryptocurrencyexchanges.checkup.common.Constants;
import com.zenolab.ax.cryptocurrencyexchanges.checkup.pin.mvp.PinCodeChangePresenter;
import com.zenolab.ax.cryptocurrencyexchanges.checkup.pin.mvp.PinCodeCheckPresenter;
import com.zenolab.ax.cryptocurrencyexchanges.checkup.pin.mvp.PinCodeContract;
import com.zenolab.ax.cryptocurrencyexchanges.checkup.pin.mvp.PinCodeCreatePresenter;
import com.zenolab.ax.cryptocurrencyexchanges.checkup.storage.Preferences;

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
