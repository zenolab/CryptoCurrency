package com.zenolab.ax.cryptocurrencyexchanges.news_api;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    private static NewsRepository newsRepository;

    public static NewsRepository getInstance() {
        if (newsRepository == null) {
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }

    private NewsApi newsApi;

    public NewsRepository() {
        newsApi = RetrofitService.cteateService(NewsApi.class);
    }

    //-----------------origin(with Search)--------------------
    /*
    NewsApi newsApi = RetrofitService.getApiClient().create(NewsApi.class);

    String country = Utils.getCountry();
    String language = Utils.getLanguage();

    Call<News> call;

        if (keyword.length() > 0 ){
        call = newsApi.getNewsSearch(keyword, language, "publishedAt", API_KEY);
    } else {
        call = newsApi.getNews(country, API_KEY);
    }
    */
    //----------------------------------------------------------
    public MutableLiveData<News> getNews(String source, String key, String keyword) {
        MutableLiveData<News> newsData = new MutableLiveData<>();

        if (keyword.length() > 0) {
            // call = newsApi.getNewsSearch(keyword, language, "publishedAt", API_KEY);
            newsApi.getNewsSearch(keyword, Utils.getLanguage(), "publishedAt", key).enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call,
                                       Response<News> response) {
                    if (response.isSuccessful()) {
                        newsData.setValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {
                    newsData.setValue(null);
                }
            });
        } else {
            // call = newsApi.getNews(country, API_KEY);
            newsApi.getNews(source, key).enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call,
                                       Response<News> response) {
                    if (response.isSuccessful()) {
                        newsData.setValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {
                    newsData.setValue(null);
                }
            });
        }

        return newsData;
    }
}
