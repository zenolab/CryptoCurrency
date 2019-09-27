package com.zenolab.ax.cryptocurrencyexchanges.crypto.mvp;



import com.zenolab.ax.cryptocurrencyexchanges.crypto.pojo.CryptoData;

import java.util.List;

public interface CryptoActivityContract {
    //------------View----------------
    interface View {
        void showData(List<CryptoData> data);

        void showError(String message);

        void showComplete();

        void showProgress();

        void hideProgress();
    }
//-------------Presenter----------
    interface Presenter {
        void loadData();
    }
}
