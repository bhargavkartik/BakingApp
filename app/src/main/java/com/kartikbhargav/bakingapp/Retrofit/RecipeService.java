package com.kartikbhargav.bakingapp.Retrofit;

import com.kartikbhargav.bakingapp.Data.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by kbhargav on 3/25/2018.
 */

public interface RecipeService {

    @GET("android-baking-app-json")
    Call<List<Recipe>> listRecipes();
}
