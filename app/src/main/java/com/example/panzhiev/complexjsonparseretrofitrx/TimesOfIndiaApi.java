package com.example.panzhiev.complexjsonparseretrofitrx;

import com.example.panzhiev.complexjsonparseretrofitrx.model.ListOfNewsItems;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Panzhiev on 24.04.2017.
 */

public interface TimesOfIndiaApi {

    @GET("feeds/newsdefaultfeeds.cms?")
    Observable<ListOfNewsItems> getNewsItems(@Query("feedtype") String feedtypeSJson);

}
