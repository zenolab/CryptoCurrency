package com.zenolab.ax.cryptocurrencyexchanges;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.zenolab.ax.cryptocurrencyexchanges.checkup.app.AppCore;
import com.zenolab.ax.cryptocurrencyexchanges.checkup.pin.mvp.PinCodeActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class InvalidateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invalidate_activity);
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
