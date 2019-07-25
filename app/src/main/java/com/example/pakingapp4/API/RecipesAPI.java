package com.example.pakingapp4.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipesAPI {
    private static Retrofit retrofit;

    private RecipesAPI() {
    }

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;

    }
}
