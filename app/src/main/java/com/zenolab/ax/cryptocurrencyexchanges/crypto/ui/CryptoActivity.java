package com.zenolab.ax.cryptocurrencyexchanges.crypto.ui;
// https://www.journaldev.com/20654/android-mvp-dagger2-retrofit-rxjava
//crypto finance
//api
// https://coinmarketcap.com/api/
// !!!! The Public API will be migrating to the new, more powerful Professional API on December 4th, 2018.
// Please update your application to use the free tier of the Professional API before then.
//  or show aaaet folder json file example

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.zenolab.ax.cryptocurrencyexchanges.InvalidateActivity;
import com.zenolab.ax.cryptocurrencyexchanges.R;
import com.zenolab.ax.cryptocurrencyexchanges.checkin.app.AppCore;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.component.CryptoRootComponent;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.component.CryptoActivityComponent;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.component.DaggerCryptoActivityComponent;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.module.CryptoActivityContextModule;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.module.CryptoActivityMvpModule;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.qualifier.ActivityContext;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.qualifier.ApplicationContext;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.mvp.CryptoActivityContract;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.mvp.PresenterImpl;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.pojo.CryptoData;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.ui.RecyclerViewAdapter;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.util.DividerItemDecoration;
import com.zenolab.ax.cryptocurrencyexchanges.news.NewsActivity;
import com.zenolab.ax.cryptocurrencyexchanges.news_api.NewsActivity2;
import com.zenolab.ax.cryptocurrencyexchanges.news_api.NewsFragment;

import java.util.List;

import javax.inject.Inject;

public class CryptoActivity extends AppCompatActivity implements CryptoActivityContract.View,
        RecyclerViewAdapter.ClickListener {

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

        presenter.loadData();
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
        startActivity(new Intent(this, NewsActivity2.class));
    }

    public void replaceFragment(Fragment fragment) {
        frameLayout.setVisibility(View.VISIBLE);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
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
    public void showData(List<CryptoData> data) {
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
}
