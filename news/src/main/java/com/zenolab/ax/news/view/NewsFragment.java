package com.zenolab.ax.news.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.zenolab.ax.news.R;
import com.zenolab.ax.news.pojo.Article;
import com.zenolab.ax.news.pojo.NewsResponse;
import com.zenolab.ax.news.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment  implements  SwipeRefreshLayout.OnRefreshListener {

    private NewsViewModel newsViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles = new ArrayList<>();
    private NewsAdapter newsAdapter;
    private String TAG = NewsActivity.class.getSimpleName();
    private TextView topHeadline;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RelativeLayout errorLayout;
    private ImageView errorImage;
    private TextView errorTitle, errorMessage;
    private Button btnRetry;


    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle bundle) {
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        topHeadline = view.findViewById(R.id.topheadelines);
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        onLoadingSwipeRefresh("");

        errorLayout = view.findViewById(R.id.errorLayout);
        errorImage = view.findViewById(R.id.errorImage);
        errorTitle = view.findViewById(R.id.errorTitle);
        errorMessage = view.findViewById(R.id.errorMessage);
        btnRetry = view.findViewById(R.id.btnRetry);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        LiveData<NewsResponse> data = newsViewModel.getData("");
        data.observe(this, newsResponseResponse -> {
            if (newsResponseResponse.getArticle() != null){

                if (!articles.isEmpty()){
                    articles.clear();
                }

                articles = newsResponseResponse.getArticle();
                newsAdapter = new NewsAdapter(articles, getActivity());
                recyclerView.setAdapter(newsAdapter);
                newsAdapter.notifyDataSetChanged();

                initListener();

                topHeadline.setVisibility(View.VISIBLE);
                swipeRefreshLayout.setRefreshing(false);

            } else {

                topHeadline.setVisibility(View.INVISIBLE);
                swipeRefreshLayout.setRefreshing(false);
                showErrorMessage(
                        R.drawable.ic_error,
                        "No Result",
                        "Please Try Again!");
            }
        } );
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater = getActivity().getMenuInflater();

        inflater.inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setQueryHint("Search Latest NewsResponse...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() > 2){
                    onLoadingSwipeRefresh(query);
                }
                else {
                    Toast.makeText(getActivity(), "Type more than two letters!", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchMenuItem.getIcon().setVisible(false, false);
    }

    private void initListener(){

        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ImageView imageView = view.findViewById(R.id.img);
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);

                Article article = articles.get(position);
                intent.putExtra("url", article.getUrl());
                intent.putExtra("title", article.getTitle());
                intent.putExtra("img",  article.getUrlToImage());
                intent.putExtra("date",  article.getPublishedAt());
                intent.putExtra("source",  article.getSource().getName());
                intent.putExtra("author",  article.getAuthor());

                Pair<View, String> pair = Pair.create((View)imageView, ViewCompat.getTransitionName(imageView));
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(),
                        pair
                );


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    startActivity(intent, optionsCompat.toBundle());
                }else {
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onRefresh() {
        onLoadingSwipeRefresh("");
    }

    private void onLoadingSwipeRefresh(final String keyword){
        swipeRefreshLayout.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        newsViewModel.refreshData(keyword);
                    }
                },3000
        );
    }

    private void showErrorMessage(int imageView, String title, String message){

        if (errorLayout.getVisibility() == View.GONE) {
            errorLayout.setVisibility(View.VISIBLE);
        }

        errorImage.setImageResource(imageView);
        errorTitle.setText(title);
        errorMessage.setText(message);

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoadingSwipeRefresh("");
            }
        });

    }


}
