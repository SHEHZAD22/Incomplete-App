package com.task.vehicleslist.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retro {
    private static Retro retrofitInstance = null;
    private ApiMethods apiMethods;
    private static final String base_url = "https://vpic.nhtsa.dot.gov/";

    private Retro(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiMethods = retrofit.create(ApiMethods.class);
    }

    public static synchronized Retro getInstance(){
        if(retrofitInstance == null) retrofitInstance = new Retro();
        return retrofitInstance;
    }

    public ApiMethods getApi(){
        return apiMethods;
    }
}
