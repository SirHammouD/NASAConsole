package com.project.alihammoud.nasaadmin;

import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

               // String credentials = Credentials.basic(username, password);
                String credentials = Credentials.basic("stanleyjobson", "swordfish");
                Request request = original.newBuilder()
                        .header("Authorization",  credentials)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        OkHttpClient client = okHttpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8082")  //Emulator
                //.baseUrl("http://192.168.0.106:8082")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        return retrofit;
    }


    public static Service getService(){
        Service service = getRetrofit().create(Service.class);

        return service;
    }


}