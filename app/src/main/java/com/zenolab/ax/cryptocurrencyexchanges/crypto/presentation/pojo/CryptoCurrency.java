package com.zenolab.ax.cryptocurrencyexchanges.crypto.presentation.pojo;

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



}
