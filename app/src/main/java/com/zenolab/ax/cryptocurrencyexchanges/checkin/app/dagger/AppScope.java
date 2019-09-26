package com.zenolab.ax.cryptocurrencyexchanges.checkin.app.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/*
 Аннотация @Retention позволяет указать,
 в какой момент жизни программного кода будет доступна аннотация: только в исходном коде,
 в скомпилированном классе или во время выполнения программы.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AppScope {
}
