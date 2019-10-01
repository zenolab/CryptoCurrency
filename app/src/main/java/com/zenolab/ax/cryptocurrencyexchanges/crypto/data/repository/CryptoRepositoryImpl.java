package com.zenolab.ax.cryptocurrencyexchanges.crypto.data.repository;

import javax.inject.Inject;
import io.reactivex.Observable;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.data.api.APIInterface;

public class CryptoRepositoryImpl implements CryptoRepository<Observable> {

    private APIInterface apiInterface;

    @Inject
    public CryptoRepositoryImpl(APIInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    @Override
    public  Observable loadData() {
        //max allowed 100
       return apiInterface.getData("40");
    }

}
