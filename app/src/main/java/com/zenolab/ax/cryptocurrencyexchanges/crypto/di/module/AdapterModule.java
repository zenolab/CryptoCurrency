package com.zenolab.ax.cryptocurrencyexchanges.crypto.di.module;

import com.zenolab.ax.cryptocurrencyexchanges.crypto.presentation.ui.CryptoActivity;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.presentation.ui.adapter.RecyclerViewAdapter;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module(includes = {CryptoActivityContextModule.class})
public class AdapterModule {

    @Provides
    @ActivityScope
    public RecyclerViewAdapter getCoinList(RecyclerViewAdapter.ClickListener clickListener) {
        return new RecyclerViewAdapter(clickListener);
    }

    @Provides
    @ActivityScope
    public RecyclerViewAdapter.ClickListener getClickListener(CryptoActivity cryptoActivity) {
        return cryptoActivity;
    }
}
