package com.zenolab.ax.news.interactor;

import androidx.lifecycle.MutableLiveData;

import com.zenolab.ax.news.pojo.NewsResponse;
import com.zenolab.ax.news.repository.NewsRepositoryImpl;

public class NewsUseCase {

    public MutableLiveData<NewsResponse> loadData(String language, String apiKey, String keyword,
                                                  MutableLiveData<NewsResponse> data) {
         return NewsRepositoryImpl.getInstance().getNews(language, apiKey,keyword,data);
    }

}
