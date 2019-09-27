package com.zenolab.ax.cryptocurrencyexchanges.crypto.retrofit;


import com.zenolab.ax.cryptocurrencyexchanges.crypto.pojo.CryptoData;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

//https://api.coinmarketcap.com/v1/ticker/?
public interface APIInterface {

    @GET("ticker/?")
    Observable<List<CryptoData>> getData(@Query("limit") String limit);
}
