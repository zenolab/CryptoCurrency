package com.zenolab.ax.cryptocurrencyexchanges.crypto.domain.interactor;

import com.zenolab.ax.cryptocurrencyexchanges.crypto.domain.mapper.CryptoLayerMapper;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.domain.sort.SortByName;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.presentation.pojo.CryptoCurrency;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.data.bean.CryptoData;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.data.repository.CryptoRepositoryImpl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class UseCaseImpl implements UseCase<Observable> {

    CryptoRepositoryImpl cryptoRepository;

    @Inject
    public UseCaseImpl(CryptoRepositoryImpl cryptoRepository) {
        this.cryptoRepository = cryptoRepository;
    }

    private static List<CryptoCurrency> inverse(List<CryptoCurrency> unsortedList) {
        Collections.reverse(unsortedList);
        return unsortedList;
    }

    /**
     * @param  - isAlphabetOrder
     * @return -  Observable<List<CryptoCurrency>> observable
     */
    @Override
    public Observable getCryptoCurrency(boolean isAlphabetOrder) {
        Observable<List<CryptoData>> observable = cryptoRepository.loadData();
        return observable.flatMap(list -> {
            return Observable.fromIterable(list)
                    .map(new Function<CryptoData, CryptoCurrency>() {
                        @Override
                        public CryptoCurrency apply(CryptoData item) throws Exception {
                            return new CryptoLayerMapper().convertFromSource(item);
                        }
                    })
                    .filter(item -> item.getPriceUsd() != null)
                    .toList()
                    .toObservable()
                    .map(unsortedList -> {
                        Comparator<CryptoCurrency> comparator = new Comparator<CryptoCurrency>() {
                            @Override
                            public int compare(CryptoCurrency left, CryptoCurrency right) {
                                return (int) (left.getPriceUsd() - right.getPriceUsd());
                            }
                        };
                        if (isAlphabetOrder) {
                            Collections.sort(unsortedList, comparator);
                        } else {
                            Collections.sort(unsortedList, new SortByName());
                        }
                        return unsortedList;
                    });
        });
    }

}
