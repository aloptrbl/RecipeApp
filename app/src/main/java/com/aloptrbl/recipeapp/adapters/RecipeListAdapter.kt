package com.aloptrbl.recipeapp.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aloptrbl.recipeapp.activities.RecipeDetailActivity
import com.aloptrbl.recipeapp.databinding.ItemRecipeListBinding
import com.aloptrbl.recipeapp.models.Meal

class RecipeListAdapter: RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    var recipes: List<Meal> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeListAdapter.ViewHolder {
        return ViewHolder(ItemRecipeListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecipeListAdapter.ViewHolder, position: Int) {
       holder.binding.recipeName = recipes[position].strMeal
        holder.binding.recipeImage = recipes[position].strMealThumb
        val url: String = recipes[position].strMealThumb
        holder.binding.headerImage.load(url)
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, RecipeDetailActivity::class.java).apply {
                putExtra("id", recipes[position].idMeal)
            }
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
    class ViewHolder(val binding: ItemRecipeListBinding) : RecyclerView.ViewHolder(binding.root)
}