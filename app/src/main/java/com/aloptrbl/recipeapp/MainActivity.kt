package com.aloptrbl.recipeapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.aloptrbl.recipeapp.adapters.CategoryListAdapter
import com.aloptrbl.recipeapp.adapters.RecipeListAdapter
import com.aloptrbl.recipeapp.databinding.ActivityMainBinding
import com.aloptrbl.recipeapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
    private val adapter = RecipeListAdapter()
    private val categoryAdapter = CategoryListAdapter()
    private var currentMealIndex = MutableLiveData<Int>()


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        binding.recyclerView.adapter = adapter
        binding.recyclerView.hasFixedSize()
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2);
        viewModel.recipeList.observe(this, Observer { recipes ->
            recipes?.let { adapter.recipes = it }
            adapter.notifyDataSetChanged()
        })

        getCategories()
        initTopAppBar()
    }

    private fun initTopAppBar() {
        val spinner = findViewById<Spinner>(R.id.action_settings)
        spinner.adapter = categoryAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = adapter.getItemId(position).toString()
                currentMealIndex.value = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun getCategories() {
        viewModel.getCategories().observe(this, Observer {
            it.body()?.let { response -> categoryAdapter.updateCategoryList(response.categories)
                currentMealIndex.observe(this) {
                    viewModel.getRecipe(response.categories[it].strCategory)
                }
            }
        })
    }

}
