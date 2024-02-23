package com.aloptrbl.recipeapp.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeEntity(
    @PrimaryKey val id: Int,
    val typeId: Int, // Use the ID of the RecipeType
    val name: String,
    val image: String,
    val ingredients: String, // Convert the List<String> to a String
    val steps: String // Convert the List<String> to a String
)

@Entity
data class RecipeType(
    @PrimaryKey val id: Int,
    val name: String
)