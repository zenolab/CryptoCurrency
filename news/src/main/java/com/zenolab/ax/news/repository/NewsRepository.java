package com.zenolab.ax.news.repository;


public interface NewsRepository<T> {

    T getNews(String source, String key, String keyword);

}
