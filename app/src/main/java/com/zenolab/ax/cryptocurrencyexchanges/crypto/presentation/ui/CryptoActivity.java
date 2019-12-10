package com.zenolab.ax.cryptocurrencyexchanges.crypto.presentation.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.zenolab.ax.cryptocurrencyexchanges.InvalidateActivity;
import com.zenolab.ax.cryptocurrencyexchanges.R;
import com.zenolab.ax.cryptocurrencyexchanges.checkup.app.AppCore;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.component.CryptoRootComponent;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.component.CryptoActivityComponent;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.component.DaggerCryptoActivityComponent;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.module.CryptoActivityContextModule;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.module.CryptoActivityMvpModule;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.qualifier.ActivityContext;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.qualifier.ApplicationContext;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.presentation.mvp.CryptoActivityContract;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.presentation.mvp.PresenterImpl;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.presentation.pojo.CryptoCurrency;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.presentation.ui.adapter.RecyclerViewAdapter;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.presentation.util.DividerItemDecoration;
import com.zenolab.ax.news.view.NewsActivity;


import java.util.List;

import javax.inject.Inject;

public class CryptoActivity extends AppCompatActivity implements CryptoActivityContract.View,
        RecyclerViewAdapter.ClickListener{

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private CryptoActivityComponent cryptoActivityComponent;
    private View layout;
    private FrameLayout frameLayout;
    private FloatingActionButton fab;
    private CoordinatorLayout.LayoutParams params;

    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;

    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Inject
    PresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        layout =  findViewById(R.id.include_scroll);
        frameLayout = (FrameLayout) findViewById(R.id.fragmentContainer);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, getResources().getString(R.string.load_info), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                showNews();
                hideFab();
            }
        });


        CryptoRootComponent cryptoRootComponent = AppCore.get(this).getCryptoRootComponent();
        cryptoActivityComponent = DaggerCryptoActivityComponent.builder()
                .cryptoActivityContextModule(new CryptoActivityContextModule(this))
                .cryptoActivityMvpModule(new CryptoActivityMvpModule(this))
                .cryptoRootComponent(cryptoRootComponent)
                .build();

        cryptoActivityComponent.injectCryptoActivity(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(activityContext));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, R.drawable.divider));
        recyclerView.setAdapter(recyclerViewAdapter);
        progressBar = findViewById(R.id.progressBar);

        presenter.loadData(false);
    }

    private void hideFab() {
        params = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        params.setBehavior(null);
        params.setAnchorId(View.NO_ID);
        fab.setLayoutParams(params);
        fab.setVisibility(View.GONE);
    }

    private void showFab() {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        params.setBehavior(new FloatingActionButton.Behavior());
        params.setAnchorId(R.id.appbar);
        fab.setLayoutParams(params);
    }

    private void showNews() {
        Intent intent = new Intent(CryptoActivity.this, NewsActivity.class);
        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, InvalidateActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void launchIntent(String name) {
        Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showData(List<CryptoCurrency> data) {
        recyclerViewAdapter.setData(data);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComplete() {

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }


    @Override
    protected void onPause(){
        super.onPause();
        layout.setVisibility(View.GONE);
    }
    @Override
    protected void onResume(){
        super.onResume();
        layout.setVisibility(View.VISIBLE);
        frameLayout.setVisibility(View.GONE);


    }
    @Override
    protected void onRestart(){
        super.onRestart();
         showFab();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.closeSource();
    }

    @Override
    public void onBackPressed() {
       exitApp();
    }
    void exitApp(){
        Intent i = new Intent();
        i.setAction(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        this.startActivity(i);
    }


}
