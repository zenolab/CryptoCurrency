package com.zenolab.ax.cryptocurrencyexchanges.crypto.presentation.pojo;

import java.util.Objects;

public class CryptoCurrency {

    private String id;
    private String name;
    private String symbol;
    private String rank;
    private Float priceUsd;
    private String priceBtc;
    private String _24hVolumeUsd;
    private String marketCapUsd;
    private String availableSupply;
    private String totalSupply;
    private Float percentChange1h;
    private Float percentChange24h;
    private Float percentChange7d;
    private String lastUpdated;

    public CryptoCurrency(String id,
                          String name,
                          String symbol,
                          String rank,
                          Float priceUsd,
                          String priceBtc,
                          String _24hVolumeUsd,
                          String marketCapUsd,
                          String availableSupply,
                          String totalSupply,
                          Float percentChange1h,
                          Float percentChange24h,
                          Float percentChange7d,
                          String lastUpdated) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.rank = rank;
        this.priceUsd = priceUsd;
        this.priceBtc = priceBtc;
        this._24hVolumeUsd = _24hVolumeUsd;
        this.marketCapUsd = marketCapUsd;
        this.availableSupply = availableSupply;
        this.totalSupply = totalSupply;
        this.percentChange1h = percentChange1h;
        this.percentChange24h = percentChange24h;
        this.percentChange7d = percentChange7d;
        this.lastUpdated = lastUpdated;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getRank() {
        return rank;
    }

    public Float getPriceUsd() {
        return priceUsd;
    }

    public String getPriceBtc() {
        return priceBtc;
    }

    public String get_24hVolumeUsd() {
        return _24hVolumeUsd;
    }

    public String getMarketCapUsd() {
        return marketCapUsd;
    }

    public String getAvailableSupply() {
        return availableSupply;
    }

    public String getTotalSupply() {
        return totalSupply;
    }

    public Float getPercentChange1h() {
        return percentChange1h;
    }

    public Float getPercentChange24h() {
        return percentChange24h;
    }

    public Float getPercentChange7d() {
        return percentChange7d;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CryptoCurrency)) return false;
        CryptoCurrency that = (CryptoCurrency) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name) &&
               Objects.equals(symbol, that.symbol) &&
               Objects.equals(rank, that.rank) &&
               Objects.equals(priceUsd, that.priceUsd) &&
               Objects.equals(priceBtc, that.priceBtc) &&
               Objects.equals(_24hVolumeUsd, that._24hVolumeUsd) &&
               Objects.equals(marketCapUsd, that.marketCapUsd) &&
               Objects.equals(availableSupply, that.availableSupply) &&
               Objects.equals(totalSupply, that.totalSupply) &&
               Objects.equals(percentChange1h, that.percentChange1h) &&
               Objects.equals(percentChange24h, that.percentChange24h) &&
               Objects.equals(percentChange7d, that.percentChange7d) &&
               Objects.equals(lastUpdated, that.lastUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, symbol, rank, priceUsd, priceBtc, _24hVolumeUsd, marketCapUsd,
                availableSupply, totalSupply, percentChange1h, percentChange24h, percentChange7d,
                lastUpdated);
    }
}
