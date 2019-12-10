package com.zenolab.ax.cryptocurrencyexchanges.checkup.pin.mvp;


import com.zenolab.ax.cryptocurrencyexchanges.R;
import com.zenolab.ax.cryptocurrencyexchanges.checkup.base.mvp.PresenterBase;
import com.zenolab.ax.cryptocurrencyexchanges.checkup.storage.Preferences;

public class PinCodeCreatePresenter extends PresenterBase<PinCodeContract.View> implements PinCodeContract.Presenter {

    private final Preferences preferences;

    public PinCodeCreatePresenter(Preferences preferences) {
        this.preferences = preferences;
    }


    @Override
    public void viewIsReady() {
        getView().showFirst(R.string.create_new_pin);
        getView().showSecond(R.string.repeat_new_pin);
    }

    @Override
    public void onTextFirst() {
        getView().focusSecond();
    }

    @Override
    public void onTextSecond() {
        if (getView().getTextFirst().equals(getView().getTextSecond())) {
            preferences.setPin(getView().getTextFirst());
            getView().showMessage(R.string.pin_created);
            getView().next();
            getView().close(false);
        } else {
            getView().showMessage(R.string.no_match);
            getView().clearAll();
            getView().focusFirst();
        }
    }

    @Override
    public void onTextThird() {
        // do nothing
    }
}
