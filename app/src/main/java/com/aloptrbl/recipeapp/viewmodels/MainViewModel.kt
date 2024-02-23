package com.aloptrbl.recipeapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.aloptrbl.recipeapp.apis.RecipeRepository
import com.aloptrbl.recipeapp.dao.RecipeDao
import com.aloptrbl.recipeapp.dao.RecipeEntity
import com.aloptrbl.recipeapp.models.Meal
import com.aloptrbl.recipeapp.models.MealResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val recipeDao: RecipeDao, private val recipeRepository: RecipeRepository) : ViewModel() {

    var recipeList = MutableLiveData<List<Meal>>()
    var recipeDetail = MutableLiveData<List<Meal>>()

    fun getCategories() = liveData {emit(recipeRepository.getCategories()) }

    // Retrieve
    fun getRecipe(category: String) {
        viewModelScope.launch {
            try {
                val recipes = withContext(Dispatchers.IO) {
                    recipeRepository.getMeals(category)
                }
                recipeList.value = recipes.body()?.meals
            } catch (e: Exception) {
                // Handle error (e.g., show an error message)
            }
        }
    }

    fun getRecipeDetail(id: String) {
        viewModelScope.launch {
            try {
                val recipes = withContext(Dispatchers.IO) {
                    recipeRepository.getMealDetail(id)
                }
                recipeDetail.value = recipes.body()?.meals
            } catch (e: Exception) {
                // Handle error (e.g., show an error message)
            }
        }
    }

    // Create
    fun createRecipe(recipe: RecipeEntity) = viewModelScope.launch {
        recipeDao.insertRecipe(recipe)
    }

    // Edit
    fun editRecipe(recipe: RecipeEntity) = viewModelScope.launch {
        recipeDao.updateRecipe(recipe)
    }

    // Delete
    fun deleteRecipe(recipe: RecipeEntity) = viewModelScope.launch {
        recipeDao.deleteRecipe(recipe)
    }
}