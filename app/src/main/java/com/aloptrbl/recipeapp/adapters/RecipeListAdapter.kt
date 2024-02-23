package com.aloptrbl.recipeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aloptrbl.recipeapp.databinding.ItemRecipeListBinding
import com.aloptrbl.recipeapp.models.Recipe

class RecipeListAdapter: RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    var recipes: List<Recipe> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeListAdapter.ViewHolder {
        return ViewHolder(ItemRecipeListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecipeListAdapter.ViewHolder, position: Int) {
       holder.binding.recipeName = recipes[position].name
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
    class ViewHolder(val binding: ItemRecipeListBinding) : RecyclerView.ViewHolder(binding.root)
}