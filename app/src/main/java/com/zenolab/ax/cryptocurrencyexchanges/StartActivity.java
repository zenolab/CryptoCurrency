package com.zenolab.ax.cryptocurrencyexchanges;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.zenolab.ax.cryptocurrencyexchanges.checkin.app.AppCore;
import com.zenolab.ax.cryptocurrencyexchanges.checkin.pin.mvp.PinCodeActivity;
import com.zenolab.ax.cryptocurrencyexchanges.checkin.storage.Preferences;


public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Preferences preferences = AppCore.getApp(this).getComponentsHolder().getAppComponent().getPreferences();

        String pin = preferences.getPin();

        // check current PIN
        if (TextUtils.isEmpty(pin)) {
            PinCodeActivity.createPinCode(this);
        } else {
            PinCodeActivity.checkPinCode(this);
        }
        finish();
    }
}
