package com.zenolab.ax.cryptocurrencyexchanges.crypto.domain.sort;

import com.zenolab.ax.cryptocurrencyexchanges.crypto.presentation.pojo.CryptoCurrency;

public class SortByPrice {
    public int compare(CryptoCurrency left, CryptoCurrency right) {
        float  result  = left.getPriceUsd() - right.getPriceUsd();
        return (int) result;
    }
}
