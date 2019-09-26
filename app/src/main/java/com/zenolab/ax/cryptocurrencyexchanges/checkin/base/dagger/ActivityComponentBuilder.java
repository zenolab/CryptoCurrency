package com.zenolab.ax.cryptocurrencyexchanges.checkin.base.dagger;

//for AppSubComponentsModule
public interface ActivityComponentBuilder<C extends ActivityComponent, M extends ActivityModule>   {
    C build();
    ActivityComponentBuilder<C,M> module(M module);
}
