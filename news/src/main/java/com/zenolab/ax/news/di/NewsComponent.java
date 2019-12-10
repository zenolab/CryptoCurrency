package com.zenolab.ax.news.di;

import com.zenolab.ax.news.viewmodel.NewsViewModel;

import dagger.Component;

@Component(modules = {InteractorModule.class})
public interface NewsComponent {

    void inject(NewsViewModel newsViewModel);


   // void inject(NewsUseCase newsUseCase);

}
