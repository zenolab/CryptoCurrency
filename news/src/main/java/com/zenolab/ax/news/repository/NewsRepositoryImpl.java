package com.zenolab.ax.news.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.zenolab.ax.news.network.NewsApi;
import com.zenolab.ax.news.network.RetrofitService;
import com.zenolab.ax.news.pojo.NewsResponse;
import com.zenolab.ax.news.util.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepositoryImpl implements NewsRepository<MutableLiveData> {

    private static final String LOG_TAG = NewsRepositoryImpl.class.getSimpleName();

    private static NewsRepositoryImpl newsRepositoryImpl;

    public static NewsRepositoryImpl getInstance() {
        if (newsRepositoryImpl == null) {
            newsRepositoryImpl = new NewsRepositoryImpl();
        }
        return newsRepositoryImpl;
    }

    private NewsApi newsApi;

    private NewsRepositoryImpl() {
        newsApi = RetrofitService.createService(NewsApi.class);
    }

    @Override
    public MutableLiveData<NewsResponse> getNews(String source, String key, String keyword,
                                                 MutableLiveData<NewsResponse> newsData) {
        Log.e(LOG_TAG, "NewsResponse key is "+keyword);
        if (keyword.length() > 0) {
            newsApi.getNewsSearch(keyword, Utils.getLanguage(), "publishedAt", key)
                    .enqueue(new Callback<NewsResponse>() {
                        @Override
                        public void onResponse(Call<NewsResponse> call,
                                               Response<NewsResponse> response) {
                            if (response.isSuccessful() && response.body().getArticle() != null) {
                                newsData.postValue(response.body());
                            } else {
                                String errorCode;
                                switch (response.code()) {
                                    case 404:
                                        errorCode = "404 not found";
                                        break;
                                    case 500:
                                        errorCode = "500 server broken";
                                        break;
                                    default:
                                        errorCode = "unknown error";
                                        break;
                                }
                                Log.e(LOG_TAG, errorCode);
                            }
                        }

                        @Override
                        public void onFailure(Call<NewsResponse> call, Throwable t) {
                            newsData.postValue(null);
                            Log.e(LOG_TAG, "Network failure");
                        }
                    });
        } else {
            newsApi.getNews(source, key).enqueue(new Callback<NewsResponse>() {
                @Override
                public void onResponse(Call<NewsResponse> call,
                                       Response<NewsResponse> response) {
                    if (response.isSuccessful()) {
                        newsData.postValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<NewsResponse> call, Throwable t) {
                    newsData.postValue(null);
                }
            });
        }

        return newsData;
    }

}
