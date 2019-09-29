package com.zenolab.ax.cryptocurrencyexchanges.news_api;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zenolab.ax.cryptocurrencyexchanges.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity2 extends AppCompatActivity
       // implements  SwipeRefreshLayout.OnRefreshListener
{

//    public static final String API_KEY = "95f2cc20ede9419bbc70851a29363807";
//    private RecyclerView recyclerView;
//    private RecyclerView.LayoutManager layoutManager;
//    private List<Article> articles = new ArrayList<>();
//    private NewsAdapter newsAdapter;
//    private String TAG = NewsActivity2.class.getSimpleName();
//    private TextView topHeadline;
//    private SwipeRefreshLayout swipeRefreshLayout;
//    private RelativeLayout errorLayout;
//    private ImageView errorImage;
//    private TextView errorTitle, errorMessage;
//    private Button btnRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

//        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
//        swipeRefreshLayout.setOnRefreshListener(this);
//        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
//
//        topHeadline = findViewById(R.id.topheadelines);
//        recyclerView = findViewById(R.id.recyclerView);
//        layoutManager = new LinearLayoutManager(NewsActivity2.this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setNestedScrollingEnabled(false);
//
//        onLoadingSwipeRefresh("");
//
//        errorLayout = findViewById(R.id.errorLayout);
//        errorImage = findViewById(R.id.errorImage);
//        errorTitle = findViewById(R.id.errorTitle);
//        errorMessage = findViewById(R.id.errorMessage);
//        btnRetry = findViewById(R.id.btnRetry);


        replaceFragment(new NewsFragment());
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

//    public void LoadJson(final String keyword){
//
//        errorLayout.setVisibility(View.GONE);
//        swipeRefreshLayout.setRefreshing(true);
//
//        NewsApi apiInterface = RetrofitService.getApiClient().create(NewsApi.class);
//
//        String country = Utils.getCountry();
//        String language = Utils.getLanguage();
//
//        Call<News> call;
//
//        if (keyword.length() > 0 ){
//            call = apiInterface.getNewsSearch(keyword, language, "publishedAt", API_KEY);
//        } else {
//            call = apiInterface.getNews(country, API_KEY);
//        }
//
//        call.enqueue(new Callback<News>() {
//            @Override
//            public void onResponse(Call<News> call, Response<News> response) {
//                if (response.isSuccessful() && response.body().getArticle() != null){
//
//                    if (!articles.isEmpty()){
//                        articles.clear();
//                    }
//
//                    articles = response.body().getArticle();
//                    newsAdapter = new NewsAdapter(articles, NewsActivity2.this);
//                    recyclerView.setAdapter(newsAdapter);
//                    newsAdapter.notifyDataSetChanged();
//
//                    initListener();
//
//                    topHeadline.setVisibility(View.VISIBLE);
//                    swipeRefreshLayout.setRefreshing(false);
//
//
//                } else {
//
//                    topHeadline.setVisibility(View.INVISIBLE);
//                    swipeRefreshLayout.setRefreshing(false);
//
//                    String errorCode;
//                    switch (response.code()) {
//                        case 404:
//                            errorCode = "404 not found";
//                            break;
//                        case 500:
//                            errorCode = "500 server broken";
//                            break;
//                        default:
//                            errorCode = "unknown error";
//                            break;
//                    }
//
//                    showErrorMessage(
//                            R.drawable.no_result,
//                            "No Result",
//                            "Please Try Again!\n"+
//                            errorCode);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<News> call, Throwable t) {
//                topHeadline.setVisibility(View.INVISIBLE);
//                swipeRefreshLayout.setRefreshing(false);
//                showErrorMessage(
//                        R.drawable.oops,
//                        "Oops..",
//                        "Network failure, Please Try Again\n"+
//                                t.toString());
//            }
//        });
//
//    }



//    private void initListener(){
//
//        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                ImageView imageView = view.findViewById(R.id.img);
//                Intent intent = new Intent(NewsActivity2.this, NewsDetailActivity.class);
//
//                Article article = articles.get(position);
//                intent.putExtra("url", article.getUrl());
//                intent.putExtra("title", article.getTitle());
//                intent.putExtra("img",  article.getUrlToImage());
//                intent.putExtra("date",  article.getPublishedAt());
//                intent.putExtra("source",  article.getSource().getName());
//                intent.putExtra("author",  article.getAuthor());
//
//                Pair<View, String> pair = Pair.create((View)imageView, ViewCompat.getTransitionName(imageView));
//                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                        NewsActivity2.this,
//                        pair
//                );
//
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                    startActivity(intent, optionsCompat.toBundle());
//                }else {
//                    startActivity(intent);
//                }
//
//            }
//        });
//
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
//
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setQueryHint("Search Latest News...");
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                if (query.length() > 2){
//                    onLoadingSwipeRefresh(query);
//                }
//                else {
//                    Toast.makeText(NewsActivity2.this, "Type more than two letters!", Toast.LENGTH_SHORT).show();
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//
//        searchMenuItem.getIcon().setVisible(false, false);

        return true;
    }

//    @Override
//    public void onRefresh() {
//        LoadJson("");
//    }
//
//    private void onLoadingSwipeRefresh(final String keyword){
//
//        swipeRefreshLayout.post(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        LoadJson(keyword);
//                    }
//                }
//        );
//
//    }
//
//    private void showErrorMessage(int imageView, String title, String message){
//
//        if (errorLayout.getVisibility() == View.GONE) {
//            errorLayout.setVisibility(View.VISIBLE);
//        }
//
//        errorImage.setImageResource(imageView);
//        errorTitle.setText(title);
//        errorMessage.setText(message);
//
//        btnRetry.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onLoadingSwipeRefresh("");
//            }
//        });
//
//    }


}
