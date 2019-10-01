package com.zenolab.ax.news.network;



import com.zenolab.ax.news.pojo.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("top-headlines")
    Call<NewsResponse> getNews(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );

    @GET("everything")
    Call<NewsResponse> getNewsSearch(
            @Query("q") String keyword,
            @Query("language") String language,
            @Query("sortBy") String sortBy,
            @Query("apiKey") String apiKey
    );
}
