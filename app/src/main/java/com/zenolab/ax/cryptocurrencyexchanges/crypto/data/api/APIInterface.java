package com.zenolab.ax.cryptocurrencyexchanges.crypto.data.api;


import com.zenolab.ax.cryptocurrencyexchanges.crypto.data.bean.CryptoData;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("ticker/?")
    Observable<List<CryptoData>> getData(@Query("limit") String limit);
}
