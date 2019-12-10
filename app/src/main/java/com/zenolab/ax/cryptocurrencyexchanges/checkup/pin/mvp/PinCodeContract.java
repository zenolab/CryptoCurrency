package com.zenolab.ax.cryptocurrencyexchanges.checkup.pin.mvp;


import com.zenolab.ax.cryptocurrencyexchanges.checkup.base.mvp.MvpPresenter;
import com.zenolab.ax.cryptocurrencyexchanges.checkup.base.mvp.MvpView;

public interface PinCodeContract {

    interface View extends MvpView {

        void showFirst(int labelResId);
        void showSecond(int labelResId);
        void showThird(int labelResId);

        String getTextFirst();
        String getTextSecond();
        String getTextThird();

        void focusFirst();
        void focusSecond();
        void focusThird();

        void clearAll();

        void showMessage(int messageResId);

        void next();

        void close(boolean flag);
    }

    interface Presenter extends MvpPresenter<View> {

        void onTextFirst();
        void onTextSecond();
        void onTextThird();
    }


}
