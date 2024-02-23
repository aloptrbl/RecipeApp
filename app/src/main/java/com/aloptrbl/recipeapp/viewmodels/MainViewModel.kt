package com.aloptrbl.recipeapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aloptrbl.recipeapp.dao.RecipeDao
import com.aloptrbl.recipeapp.models.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val recipeDao: RecipeDao) : ViewModel() {

    val recipeList = MutableLiveData<List<Recipe>>()

    // Retrieve
    fun getRecipe(id: Int) = viewModelScope.launch {
        val recipe = recipeDao.getRecipe(id)
        // Do something with the recipe
    }

    // Create
    fun createRecipe(recipe: Recipe) = viewModelScope.launch {
        recipeDao.insertRecipe(recipe)
    }

    // Edit
    fun editRecipe(recipe: Recipe) = viewModelScope.launch {
        recipeDao.updateRecipe(recipe)
    }

    // Delete
    fun deleteRecipe(recipe: Recipe) = viewModelScope.launch {
        recipeDao.deleteRecipe(recipe)
    }
}