package com.zenolab.ax.cryptocurrencyexchanges.checkup.base.dagger;


public interface ActivityComponentBuilder<C extends ActivityComponent, M extends ActivityModule>   {
    C build();
    ActivityComponentBuilder<C,M> module(M module);
}
