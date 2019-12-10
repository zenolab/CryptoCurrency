package com.zenolab.ax.news.repository;


import androidx.lifecycle.MutableLiveData;

import com.zenolab.ax.news.pojo.NewsResponse;

public interface NewsRepository<T> {

    T getNews(String source, String key, String keyword, MutableLiveData<NewsResponse> newsData);

}
