package com.zenolab.ax.cryptocurrencyexchanges.crypto.domain.sort;

import com.zenolab.ax.cryptocurrencyexchanges.crypto.presentation.pojo.CryptoCurrency;

import java.util.Comparator;

public class SortByName implements Comparator<CryptoCurrency> {
    // Used for sorting in ascending order of
    public int compare(CryptoCurrency o1, CryptoCurrency o2)
    {
        return o1.getSymbol().compareToIgnoreCase(o2.getSymbol());
    }
}