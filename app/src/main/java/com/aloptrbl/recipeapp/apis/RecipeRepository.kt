package com.aloptrbl.recipeapp.apis

import com.aloptrbl.recipeapp.dao.RecipeDao
import com.aloptrbl.recipeapp.models.Category
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository @Inject constructor(private val recipeAPI: RecipeAPI, private val recipeDao: RecipeDao) {
    suspend fun getCategories() = recipeAPI.getCategories()
    suspend fun getMeals(category: String) = recipeAPI.getMealsByCategory(category)

    suspend fun getMealDetail(id: String) = recipeAPI.getMealDetail(id)
}