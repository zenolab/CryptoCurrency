package com.zenolab.ax.cryptocurrencyexchanges.checkup.pin.mvp;


import com.zenolab.ax.cryptocurrencyexchanges.R;
import com.zenolab.ax.cryptocurrencyexchanges.checkup.base.mvp.PresenterBase;
import com.zenolab.ax.cryptocurrencyexchanges.checkup.storage.Preferences;

public class PinCodeChangePresenter extends PresenterBase<PinCodeContract.View> implements PinCodeContract.Presenter {

    private final Preferences preferences;

    public PinCodeChangePresenter(Preferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public void viewIsReady() {
        getView().showFirst(R.string.enter_old_pin);
        getView().showSecond(R.string.create_new_pin);
        getView().showThird(R.string.repeat_new_pin);
    }

    @Override
    public void onTextFirst() {
        getView().focusSecond();
    }

    @Override
    public void onTextSecond() {
        getView().focusThird();
    }

    @Override
    public void onTextThird() {
        if (!getView().getTextFirst().equals(preferences.getPin())) {
            getView().showMessage(R.string.wrong_pin);
            getView().clearAll();
            getView().focusFirst();
            return;
        }

        if (getView().getTextSecond().equals(getView().getTextThird())) {
            preferences.setPin(getView().getTextSecond());
            getView().showMessage(R.string.pin_changed);
            getView().close(false);
        } else {
            getView().showMessage(R.string.no_match);
            getView().clearAll();
            getView().focusFirst();
        }
    }
}
