package com.example.panzhiev.complexjsonparseretrofitrx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.panzhiev.complexjsonparseretrofitrx.adapters.NewsRecyclerAdapter;
import com.example.panzhiev.complexjsonparseretrofitrx.model.ListOfNewsItems;
import com.example.panzhiev.complexjsonparseretrofitrx.model.NewsItem;
import com.example.panzhiev.complexjsonparseretrofitrx.utils.Config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = "MainActivity";

    private Button mButtonGetData;
    private RecyclerView mRecyclerView;
    List<NewsItem> mNewsItems;
    NewsRecyclerAdapter adapter;
    Gson gson = new GsonBuilder().create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonGetData = (Button) findViewById(R.id.button_get_data);
        mButtonGetData.setOnClickListener(this);

        mNewsItems = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_person);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(llm);

        adapter = new NewsRecyclerAdapter(this, mNewsItems);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_get_data:
                query();
                mRecyclerView.getAdapter().notifyDataSetChanged();
                break;
            case R.id.card_view_item:
                showCurentNews();
                break;
            default:
                break;
        }
    }

    private void showCurentNews() {

        Intent intent = new Intent(MainActivity.this, ShowCurrentNews.class);
        startActivity(intent);
    }

    private void query() {

        Observable<ListOfNewsItems> observableNewsItems = App.getApi().getNewsItems(Config.RESPONSE_TYPE_JSON);

        observableNewsItems.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ListOfNewsItems>() {
                    @Override
                    public void onCompleted() {
                        Log.d(LOG_TAG, " onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(LOG_TAG, "onError()");
                        Log.d(LOG_TAG, e.getMessage());
                        if (e instanceof HttpException) {
                            HttpException response = (HttpException)e;
                            int code = response.code();
                            Log.d(LOG_TAG, String.valueOf(code));
                        }
                    }

                    @Override
                    public void onNext(ListOfNewsItems listNewsItems) {
                        Log.d(LOG_TAG, listNewsItems.toString() + " response NewsItems");

                        mNewsItems = listNewsItems.getNewsItem();
                        adapter = new NewsRecyclerAdapter(MainActivity.this, mNewsItems);
                        mRecyclerView.setAdapter(adapter);

                        for (NewsItem newsItem : mNewsItems){
                            Log.d(LOG_TAG, newsItem.getHeadLine());
                        }
                    }
                });
    }
}
