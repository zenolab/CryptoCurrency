package com.zenolab.ax.news.di;

import com.zenolab.ax.news.interactor.NewsUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {

    @Provides
    public NewsUseCase provideNewsUseCaseImpl() {
        return new NewsUseCase();
    }

}
