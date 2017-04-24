package com.example.panzhiev.complexjsonparseretrofitrx;

import android.app.Application;

import com.example.panzhiev.complexjsonparseretrofitrx.Utils.AppConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by Panzhiev on 24.04.2017.
 */

public class App extends Application {

    private static TimesofindiaApi timesOfIndiaApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        Gson gson = new GsonBuilder().create();
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL) //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create(gson)) //Конвертер, необходимый для преобразования JSON'а в объекты
                .addCallAdapterFactory(rxAdapter)
                .build();
        timesOfIndiaApi = retrofit.create(TimesofindiaApi.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    public static TimesofindiaApi getApi() {
        return timesOfIndiaApi;
    }
}
