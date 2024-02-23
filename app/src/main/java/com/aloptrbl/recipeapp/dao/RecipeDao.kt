package com.aloptrbl.recipeapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface RecipeDao {

    @Query("SELECT * FROM RecipeEntity")
    suspend fun getAllRecipes(): List<RecipeEntity>

    @Query("SELECT * FROM RecipeEntity WHERE id = :id")
    suspend fun getRecipe(id: Int): RecipeEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeEntity)

    @Update
    suspend fun updateRecipe(recipe: RecipeEntity)

    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)
}