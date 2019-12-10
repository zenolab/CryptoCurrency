package com.zenolab.ax.cryptocurrencyexchanges.checkup.pin.mvp;


import com.zenolab.ax.cryptocurrencyexchanges.R;
import com.zenolab.ax.cryptocurrencyexchanges.checkup.base.mvp.PresenterBase;
import com.zenolab.ax.cryptocurrencyexchanges.checkup.storage.Preferences;

public class PinCodeCheckPresenter extends PresenterBase<PinCodeContract.View> implements PinCodeContract.Presenter {

    private final Preferences preferences;

    public PinCodeCheckPresenter(Preferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public void viewIsReady() {
        getView().showFirst(R.string.enter_pin);
    }

    @Override
    public void onTextFirst() {
        if (getView().getTextFirst().equals(preferences.getPin())) {
            getView().next();
            getView().close(false);
        } else {
            getView().showMessage(R.string.wrong_pin);
            getView().clearAll();
        }
    }

    @Override
    public void onTextSecond() {
        //do nothing
    }

    @Override
    public void onTextThird() {
        //do nothing
    }
}
