package com.zenolab.ax.cryptocurrencyexchanges.crypto.presentation.mvp;

import com.zenolab.ax.cryptocurrencyexchanges.crypto.presentation.pojo.CryptoCurrency;

import java.util.List;

public interface CryptoActivityContract {
    interface View {
        void showData(List<CryptoCurrency> data);

        void showError(String message);

        void showComplete();

        void showProgress();

        void hideProgress();
    }
    interface Presenter {
        void loadData(boolean isSort);

        void closeSource();
    }
}
