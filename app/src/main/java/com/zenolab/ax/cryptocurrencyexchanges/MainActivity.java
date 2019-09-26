package com.zenolab.ax.cryptocurrencyexchanges;
//http://startandroid.ru/ru/blog/473-mvp-na-primere-jekrana-s-pin-kodom.html
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.zenolab.ax.cryptocurrencyexchanges.checkin.app.AppCore;
import com.zenolab.ax.cryptocurrencyexchanges.checkin.pin.mvp.PinCodeActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


//for ndk cheating
//http://qaru.site/questions/501616/error-no-toolchains-found-in-the-ndk-toolchains-folder-for-abi-with-prefix-llvm
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.change_pin)
    void onChangePinClick() {
        PinCodeActivity.changePinCode(this);
    }

    @OnClick(R.id.reset_pin)
    void onResetPinClick() {
        AppCore.getApp(this).getComponentsHolder().getAppComponent().getPreferences().setPin("");
    }
}
