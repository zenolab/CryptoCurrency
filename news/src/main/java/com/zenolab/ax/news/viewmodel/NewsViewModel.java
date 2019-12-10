package com.zenolab.ax.news.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.zenolab.ax.news.di.DaggerInjector;
import com.zenolab.ax.news.interactor.NewsUseCase;
import com.zenolab.ax.news.pojo.NewsResponse;
import com.zenolab.ax.news.util.Utils;


import javax.inject.Inject;

import static com.zenolab.ax.news.common.Const.API_KEY;


public class NewsViewModel extends ViewModel {
    private static final String LOG_TAG = NewsViewModel.class.getSimpleName();

    private MutableLiveData<NewsResponse> data;

    @Inject
    NewsUseCase newsUseCase;

    public NewsViewModel() {
        DaggerInjector.getInstance();
        DaggerInjector.getComponent().inject(this);
    }

    public LiveData<NewsResponse> getData(String keyword) {
        if (data == null) {
            data = new MutableLiveData<>();
        }
        loadData(keyword);
        return data;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(LOG_TAG, "on cleared called");
    }

    private void loadData(String keyword) {
        data = newsUseCase.loadData(Utils.getCountry(),API_KEY,keyword,data);
    }

    public void refreshData(String keyword) {
        getData(keyword);
    }

}
