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
   //конструктор
    public PinCodeActivityModule(Constants.PinCodeMode pinCodeMode) {
        this.pinCodeMode = pinCodeMode;
    }

    //Для создания презентера тут используется даггер
    //В зависимости от режима, мы создаем один из трех презентеров.
    //Это три разных класса, но все они реализуют интерфейс PinCodeContract.Presenter.
    //А в PinCodeActivity даггер помещает созданный презентер в поле
    //PinCodeContract.Presenter presenter;
    //Далее в onCreate мы даем созданному презентеру View (оно же Activity) и сообщаем, что все готово к работе.

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
                return null;
        }
    }
}
