package com.zenolab.ax.cryptocurrencyexchanges.crypto.di.component;

import android.content.Context;

import com.zenolab.ax.cryptocurrencyexchanges.crypto.CryptoActivity;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.CryptoFragment;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.module.AdapterModule;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.module.CryptoActivityMvpModule;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.module.CryptoFragmentContextModule;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.qualifier.ActivityContext;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.scopes.ActivityScope;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.scopes.FragmentScope;

import dagger.Component;

//@FragmentScope
@ActivityScope
@Component(dependencies = CryptoRootComponent.class,
        modules = {AdapterModule.class, CryptoFragmentContextModule.class})
public interface CryptoFragmentComponent {
    @ActivityContext
    Context getContext();

   // void injectCryptoFragment(CryptoFragment cryptoFragment);
}
