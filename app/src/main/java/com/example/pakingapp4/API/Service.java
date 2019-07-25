package com.example.pakingapp4.API;

import com.example.pakingapp4.model.Recipe;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("baking.json")
    Call<List<Recipe>> getRecipes();

}