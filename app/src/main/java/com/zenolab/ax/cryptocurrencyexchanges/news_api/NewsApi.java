package com.zenolab.ax.cryptocurrencyexchanges.news_api;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("top-headlines")
    Call<News> getNews(

            @Query("country") String country,
            @Query("apiKey") String apiKey

    );

    //------------------
    @GET("top-headlines")
    Call<NewsResponse> getNewsList(
            @Query("sources") String newsSource,
            @Query("apiKey") String apiKey
    );
    //------------------


    @GET("everything")
    Call<News> getNewsSearch(

            @Query("q") String keyword,
            @Query("language") String language,
            @Query("sortBy") String sortBy,
            @Query("apiKey") String apiKey

    );

}
