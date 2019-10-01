package com.zenolab.ax.news.di;

public class DaggerInjector {

    private static volatile DaggerInjector instance;

    private static NewsComponent appComponent;

    private DaggerInjector() {
        appComponent = DaggerNewsComponent.builder().build();
    }

    public static NewsComponent getComponent() {
        return appComponent;
    }

    //Double Checked Locking
    public static DaggerInjector getInstance() {
        DaggerInjector localInstance = instance;
        if (localInstance == null) {
            synchronized (DaggerInjector.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DaggerInjector();
                }
            }
        }
        return localInstance;
    }

}
