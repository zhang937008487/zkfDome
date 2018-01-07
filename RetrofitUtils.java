package com.example.summary_dome.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by z on 2018/1/6.
 */

public class RetrofitUtils {
    private static volatile RetrofitUtils instance;
    private final ApiService apiService;

    public RetrofitUtils(String baseUrl){
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new OkHttpClient())
                .baseUrl(baseUrl)
                .build();
        apiService = retrofit.create(ApiService.class);
    }
    public static RetrofitUtils getInstance(String baseUrl){
        if(instance==null){
            synchronized (RetrofitUtils.class){
                if(null==instance){
                    instance=new RetrofitUtils(baseUrl);
                }
            }
        }
        return instance;
    }
    public ApiService getApiService(){
        return apiService;
    }

}
