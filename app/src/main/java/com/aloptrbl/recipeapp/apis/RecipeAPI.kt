package com.aloptrbl.recipeapp.apis

import com.aloptrbl.recipeapp.models.CategoriesResponse
import com.aloptrbl.recipeapp.models.MealResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeAPI {
    @GET("categories.php")
    suspend fun getCategories(): Response<CategoriesResponse>

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): Response<MealResponse>

    @GET("lookup.php")
    suspend fun getMealDetail(@Query("i") id: String): Response<MealResponse>
}