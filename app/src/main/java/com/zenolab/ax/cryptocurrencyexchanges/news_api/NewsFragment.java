package com.zenolab.ax.cryptocurrencyexchanges.news_api;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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

public class NewsFragment extends Fragment  implements  SwipeRefreshLayout.OnRefreshListener {

    private NewsViewModel newsViewModel;

    public static final String API_KEY = "95f2cc20ede9419bbc70851a29363807";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles = new ArrayList<>();
    private NewsAdapter newsAdapter;
    private String TAG = NewsActivity2.class.getSimpleName();
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
        //---------------------------------------------------------
//        toolbar.setBackgroundColor(ColorsUtils.getCompanyColor());
//        toolbar.setNavigationOnClickListener(v -> onBackPressed());
//        toolbar.inflateMenu(R.menu.menu_settings);
//        menuItem = toolbar.getMenu().findItem(R.id.action_inspection_details_settings);
//        menuItem.setOnMenuItemClickListener(item1 -> showBottomSheetDialog());
        //---------------------------------------------------------
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

        //---------------------------------------------------------
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        LiveData<News> data = newsViewModel.getData("");
        data.observe(this,newsResponse -> {
            if (newsResponse.getArticle() != null){

                if (!articles.isEmpty()){
                    articles.clear();
                }

                articles = newsResponse.getArticle();
                newsAdapter = new NewsAdapter(articles, getActivity());
                recyclerView.setAdapter(newsAdapter);
                newsAdapter.notifyDataSetChanged();

                //temp
                initListener();

                topHeadline.setVisibility(View.VISIBLE);
                swipeRefreshLayout.setRefreshing(false);
                //end temp


            }
            //----------------------------------------
        } );

        // TODO: Use the ViewModel
//        LiveData<String> data = newsViewModel.getNews();
//        data.observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                // ...
//            }
//        });

        //------------------------------------------
        // https://www.simplifiedcoding.net/android-viewmodel-using-retrofit/
//        newsViewModel.getHeroes().observe(this, new Observer<List<Article>>() {
//            @Override
//            public void onChanged(@Nullable List<Article> heroList) {
//               // adapter = new HeroesAdapter(getActivity(), heroList);
//               // recyclerView.setAdapter(adapter);
//            }
//        });
        //------------------------------------------


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
        searchView.setQueryHint("Search Latest News...");
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


        public void LoadJsonFragment(final String keyword){

        errorLayout.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(true);

        NewsApi newsApi = RetrofitService.getApiClient().create(NewsApi.class);

        String country = Utils.getCountry();
        String language = Utils.getLanguage();

        Call<News> call;

        if (keyword.length() > 0 ){
            call = newsApi.getNewsSearch(keyword, language, "publishedAt", API_KEY);
        } else {
            call = newsApi.getNews(country, API_KEY);
        }

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() && response.body().getArticle() != null){

                    if (!articles.isEmpty()){
                        articles.clear();
                    }

                    articles = response.body().getArticle();
                    newsAdapter = new NewsAdapter(articles, getActivity());
                    recyclerView.setAdapter(newsAdapter);
                    newsAdapter.notifyDataSetChanged();

                    //temp
                    initListener();

                    topHeadline.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setRefreshing(false);
                    //end temp


                } else {

                    topHeadline.setVisibility(View.INVISIBLE);
                    swipeRefreshLayout.setRefreshing(false);

                    String errorCode;
                    switch (response.code()) {
                        case 404:
                            errorCode = "404 not found";
                            break;
                        case 500:
                            errorCode = "500 server broken";
                            break;
                        default:
                            errorCode = "unknown error";
                            break;
                    }

                    showErrorMessage(
                            R.drawable.no_result,
                            "No Result",
                            "Please Try Again!\n"+
                            errorCode);

                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                topHeadline.setVisibility(View.INVISIBLE);
                swipeRefreshLayout.setRefreshing(false);
                showErrorMessage(
                        R.drawable.oops,
                        "Oops..",
                        "Network failure, Please Try Again\n"+
                        t.toString());
            }
        });

    }


//    public void LoadJsonFragment(final String keyword){
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
//                    newsAdapter = new NewsAdapter(articles, getActivity());
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
//                        t.toString());
//            }
//        });
//
//    }


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
       // LoadJsonFragment("");
    }

    private void onLoadingSwipeRefresh(final String keyword){

        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                      //  LoadJsonFragment(keyword); //start load
                        newsViewModel.refreshData("putin");
                    }
                }
        );

        //test
        // newsViewModel.refreshData(keyword); //  on a null object reference

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
