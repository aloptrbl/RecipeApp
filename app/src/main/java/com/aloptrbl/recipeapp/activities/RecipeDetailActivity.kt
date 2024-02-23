package com.aloptrbl.recipeapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.aloptrbl.recipeapp.R
import com.aloptrbl.recipeapp.databinding.ActivityRecipeDetailBinding
import com.aloptrbl.recipeapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityRecipeDetailBinding>(this, R.layout.activity_recipe_detail)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        val intent = intent
        val id = intent.getStringExtra("id")

        id?.let {
            viewModel.getRecipeDetail(it)
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}