package com.example.panzhiev.complexjsonparseretrofitrx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.panzhiev.complexjsonparseretrofitrx.model.NewsItem;

import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = "MainActivity";

    private Button mButtonGetData;
    private TextView mTextViewResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonGetData = (Button) findViewById(R.id.button_get_data);
        mButtonGetData.setOnClickListener(this);
        mTextViewResponse = (TextView) findViewById(R.id.text_view_response);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_get_data:
                query();
                break;
            default:
                break;
        }
    }

    private void query() {

        Observable<List<NewsItem>> observableNewsItems = App.getApi().getNewsItems();

        observableNewsItems.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<NewsItem>>() {
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
                    public void onNext(List<NewsItem> listNewsItems) {
                        Log.d(LOG_TAG, listNewsItems.toString() + " response NewsItems");

//                        Map map = gson.fromJson(object.toString(), Map.class);
//                        String translation = map.get("text").toString();
//                        translation = translation.replace("]", "").replace("[", "");
//                        mEditText_first.setText(translation);
                    }
                });
    }
}
