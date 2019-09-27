package com.zenolab.ax.cryptocurrencyexchanges.crypto;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.zenolab.ax.cryptocurrencyexchanges.R;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.component.CryptoActivityComponent;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.qualifier.ActivityContext;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.di.qualifier.ApplicationContext;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.mvp.CryptoActivityContract;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.mvp.PresenterImpl;
import com.zenolab.ax.cryptocurrencyexchanges.crypto.pojo.CryptoData;

import java.util.List;

import javax.inject.Inject;

public class CryptoFragment extends Fragment implements CryptoActivityContract.View,
        RecyclerViewAdapter.ClickListener {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    CryptoActivityComponent cryptoActivityComponent;

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

    public CryptoFragment() {
        // Required empty public constructor
    }


    public static CryptoFragment newInstance(String param1, String param2) {
        CryptoFragment fragment = new CryptoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crypto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        CryptoRootComponent applicationComponent = AppCore.get(getActivity()).getCryptoRootComponent();
//        cryptoActivityComponent = DaggerCryptoFragmentComponent.builder()
//                .cryptoActivityContextModule(new CryptoActivityContextModule(getActivity()))
//                .cryptoActivityMvpModule(new CryptoActivityMvpModule(this))
//                .applicationComponent(applicationComponent)
//                .build();
//
//        cryptoActivityComponent.injectCryptoFragment(this);
//
//        recyclerView = view.findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(activityContext));
//        recyclerView.setAdapter(recyclerViewAdapter);
//        progressBar = view.findViewById(R.id.progressBar);
//
//        presenter.loadData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void launchIntent(String name) {
        Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
        // startActivity(new Intent(activityContext, DetailActivity.class).putExtra("name", name));
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

}
