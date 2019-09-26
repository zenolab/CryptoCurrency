package com.zenolab.ax.cryptocurrencyexchanges.checkin.base.mvp;

public abstract class PresenterBase<T extends MvpView> implements MvpPresenter<T> {

    private T view;

    @Override
    public void attachView(T mvpView) {
        view = mvpView;
    }

    @Override
    public void detachView() {
        view = null;
    }

    public T getView() {
        return view;
    }

    /*
    Тернарный оператор
    myVariable = (testCondition) ? someValue : anotherValue;
    где

    () содержит if
    ? означает then
    : означает else
     */
    protected boolean isViewAttached() {
        //сокращенная запись if
        //Если view == null,возвращает false,иначе true
        return view != null;
    }

    //virtual method
    @Override
    public void destroy() {

    }
}
