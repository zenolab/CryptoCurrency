package com.zenolab.ax.news.interactor;

import androidx.lifecycle.MutableLiveData;

import com.zenolab.ax.news.pojo.NewsResponse;
import com.zenolab.ax.news.repository.NewsRepositoryImpl;
import com.zenolab.ax.news.util.Utils;



public class NewsUseCase {


    public MutableLiveData<NewsResponse> loadData(String language,String apiKey,String keyword) {
         return NewsRepositoryImpl.getInstance().getNews(language, apiKey,keyword);
    }

}
