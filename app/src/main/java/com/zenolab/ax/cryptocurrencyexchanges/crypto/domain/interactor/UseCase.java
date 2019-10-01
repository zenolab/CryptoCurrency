package com.zenolab.ax.cryptocurrencyexchanges.crypto.domain.interactor;

public interface UseCase<T> {

    T getCryptoCurrency(boolean isAlphabetOrder);
}
