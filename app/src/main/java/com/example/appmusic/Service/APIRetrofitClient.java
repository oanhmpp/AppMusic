package com.example.appmusic.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIRetrofitClient {
    public static Retrofit retrofit = null;

    // function tra ve cau hinh sau khi tao retrofit
    public static Retrofit getClient( String base_url){
        // tuong tac voi giao thuc mang
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                                        .readTimeout( 100000, TimeUnit.MILLISECONDS)
                                        .writeTimeout( 100000,TimeUnit.MILLISECONDS)
                                        .connectTimeout( 100000,TimeUnit.MILLISECONDS)
                                        .retryOnConnectionFailure(true)
                                        .protocols( Arrays.asList(Protocol.HTTP_1_1))
                                        .build();
        Gson gson =  new GsonBuilder().setLenient().create();

        retrofit = new  Retrofit.Builder().baseUrl(base_url)
                                        .client(okHttpClient)
                                        .addConverterFactory(GsonConverterFactory.create(gson))
                                        .build();
        return retrofit;
    }
}
