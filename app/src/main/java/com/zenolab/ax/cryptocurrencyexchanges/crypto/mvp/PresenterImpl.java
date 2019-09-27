package com.zenolab.ax.cryptocurrencyexchanges.crypto.mvp;



import com.zenolab.ax.cryptocurrencyexchanges.crypto.pojo.CryptoData;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.retrofit.APIInterface;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class PresenterImpl implements CryptoActivityContract.Presenter {

    APIInterface apiInterface;
    CryptoActivityContract.View view;

    @Inject
    public PresenterImpl(APIInterface apiInterface, CryptoActivityContract.View mView) {
        this.apiInterface = apiInterface;
        this.view = mView;
    }

    @Override
    public void loadData() {

        view.showProgress();

        //max allowed 100
        apiInterface.getData("100")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CryptoData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<CryptoData> data) {
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
}
