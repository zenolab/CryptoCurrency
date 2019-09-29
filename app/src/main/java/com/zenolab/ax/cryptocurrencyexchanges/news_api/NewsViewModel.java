package com.zenolab.ax.cryptocurrencyexchanges.news_api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.zenolab.ax.cryptocurrencyexchanges.news_api.Const.API_KEY;

public class NewsViewModel extends ViewModel {
    private static final String LOG_TAG = NewsViewModel.class.getSimpleName();

    private MutableLiveData<News> mutableLiveData;
    private MutableLiveData<News> data;
    private NewsRepository newsRepository;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        newsRepository = NewsRepository.getInstance();
       // mutableLiveData = newsRepository.getNews("google-news", "95f2cc20ede9419bbc70851a29363807");
        mutableLiveData = newsRepository.getNews(Utils.getCountry(), API_KEY,"");
        mutableLiveData = newsRepository.getNews(Utils.getCountry(), API_KEY,"Ukraine"); //work

    }


    public LiveData<News> getData(String keyword) {
        if (data == null) {
            data = new MutableLiveData<>();
            loadData(keyword);
        }
        return data;
    }

    private void loadData(String keyword) {
        newsRepository = NewsRepository.getInstance();
        data = newsRepository.getNews(Utils.getCountry(), API_KEY,keyword);
    }

    public LiveData<News> getNewsRepository() {
        return mutableLiveData;
    }


    public void refreshData(String keyword) {
        getData(keyword);
    }


}
