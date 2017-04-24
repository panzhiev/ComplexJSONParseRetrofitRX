package com.example.panzhiev.complexjsonparseretrofitrx;

import com.example.panzhiev.complexjsonparseretrofitrx.model.NewsItem;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Panzhiev on 24.04.2017.
 */

public interface TimesofindiaApi {

    @GET("feeds/newsdefaultfeeds.cms?feedtype=sjson")
    Observable<List<NewsItem>> getNewsItems();

}
