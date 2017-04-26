package com.example.panzhiev.complexjsonparseretrofitrx;

import com.example.panzhiev.complexjsonparseretrofitrx.model.ListOfNewsItems;
import com.example.panzhiev.complexjsonparseretrofitrx.utils.Config;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Panzhiev on 24.04.2017.
 */

public interface TimesOfIndiaApi {

    @GET(Config.URL_NEWS_DEFAULT_FEED)
    Observable<ListOfNewsItems> getNewsItems(@Query(Config.PARAM_FEED_TYPE) String feedtypeSJson);

}
