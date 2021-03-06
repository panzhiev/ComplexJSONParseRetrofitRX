package com.example.panzhiev.complexjsonparseretrofitrx;

import android.app.Application;

import com.example.panzhiev.complexjsonparseretrofitrx.utils.Config;
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

    private static TimesOfIndiaApi timesOfIndiaApi;
    private Retrofit retrofit;

    public Gson gson = new GsonBuilder().create();

    @Override
    public void onCreate() {
        super.onCreate();
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL) //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create(gson)) //Конвертер, необходимый для преобразования JSON'а в объекты
                .addCallAdapterFactory(rxAdapter)
                .build();
        timesOfIndiaApi = retrofit.create(TimesOfIndiaApi.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    public static TimesOfIndiaApi getApi() {
        return timesOfIndiaApi;
    }
}
