package com.zenolab.ax.cryptocurrencyexchanges.checkin.app.dagger;

import com.zenolab.ax.cryptocurrencyexchanges.checkin.base.dagger.ActivityComponentBuilder;
import com.zenolab.ax.cryptocurrencyexchanges.checkin.pin.dagger.PinCodeActivityComponent;
import com.zenolab.ax.cryptocurrencyexchanges.checkin.pin.mvp.PinCodeActivity;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;


// subcomponents - Для Сабкомпонента доступны все объекты родителя
@Module(subcomponents = {PinCodeActivityComponent.class})
public class AppSubComponentsModule {

    @Provides
    @IntoMap //Для того чтобы добавить элемент в Map
    @ClassKey(PinCodeActivity.class) //Если ключи вашеего Map могут быть ограничены, попробуйте вместо этого использовать пользовательскую аннотацию,член, тип которого {@code Class <? extends Something>}.
    ActivityComponentBuilder provideSplashViewBuilder(PinCodeActivityComponent.Builder builder) {
        return builder;
    }

}
