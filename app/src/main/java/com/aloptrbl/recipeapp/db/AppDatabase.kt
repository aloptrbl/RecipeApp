package com.aloptrbl.recipeapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aloptrbl.recipeapp.dao.RecipeDao
import com.aloptrbl.recipeapp.models.Recipe

@Database(entities = [Recipe::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}