package com.zenolab.ax.cryptocurrencyexchanges.crypto.domain.sort;

import com.zenolab.ax.cryptocurrencyexchanges.crypto.presentation.pojo.CryptoCurrency;

public class SortByPercent {
    public int compare(CryptoCurrency left, CryptoCurrency right) {
        float  result  = left.getPercentChange1h() - right.getPercentChange1h() ;
        return (int) result;
    }
}
