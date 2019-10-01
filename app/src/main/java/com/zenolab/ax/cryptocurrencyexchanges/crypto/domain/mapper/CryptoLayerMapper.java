package com.zenolab.ax.cryptocurrencyexchanges.crypto.domain.mapper;

import com.zenolab.ax.cryptocurrencyexchanges.crypto.presentation.pojo.CryptoCurrency;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.data.bean.CryptoData;

public class CryptoLayerMapper {

    public CryptoCurrency convertFromSource(CryptoData it) {
        return new CryptoCurrency(it.id,
                it.name,
                it.symbol,
                it.rank,
                Float.parseFloat(it.priceUsd),
                it.priceBtc,
                it._24hVolumeUsd,
                it.marketCapUsd,
                it.availableSupply,
                it.totalSupply,
                Float.parseFloat(it.percentChange1h),
                Float.parseFloat(it.percentChange24h),
                Float.parseFloat(it.percentChange7d),
                it.lastUpdated);
    }
}
