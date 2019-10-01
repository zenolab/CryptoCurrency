package com.zenolab.ax.cryptocurrencyexchanges.crypto.presentation.mvp;

import com.zenolab.ax.cryptocurrencyexchanges.crypto.domain.interactor.UseCaseImpl;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.presentation.pojo.CryptoCurrency;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PresenterImpl implements CryptoActivityContract.Presenter {

    private static final String LOG_TAG = PresenterImpl.class.getSimpleName();

    private CryptoActivityContract.View view;
    private UseCaseImpl interactor;
    private Disposable disposable ;


    @Inject
    public PresenterImpl(CryptoActivityContract.View view, UseCaseImpl interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void loadData(boolean isSortOrder) {
        view.showProgress();

        Observable<List<CryptoCurrency>> ob = interactor.getCryptoCurrency(isSortOrder);
        ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CryptoCurrency>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(List<CryptoCurrency> data) {
                        view.showData(data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError("Error occurred");
                        view.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                        view.showComplete();
                        view.hideProgress();
                    }
                });

    }

    @Override
    public void closeSource() {
        disposable.dispose();
    }

}
