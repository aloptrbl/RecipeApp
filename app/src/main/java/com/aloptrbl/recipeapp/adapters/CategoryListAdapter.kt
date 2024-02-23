package com.aloptrbl.recipeapp.adapters

import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SpinnerAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aloptrbl.recipeapp.R
import com.aloptrbl.recipeapp.databinding.ItemRecipeListBinding
import com.aloptrbl.recipeapp.models.Category

class CategoryListAdapter : BaseAdapter(), SpinnerAdapter {

    var categories: List<Category> = emptyList()

    override fun getCount(): Int {
        return categories.size
    }

    override fun getItem(position: Int): Any {
        return categories[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    public fun updateCategoryList(categories: List<Category>) {
        this.categories = categories
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView = convertView ?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_category_list, parent, false)

        val category = categories[position]
        val textView = itemView.findViewById<TextView>(R.id.categoryName)
        textView.text = category.strCategory

        return itemView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val dropDownView = convertView ?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_category_list_dropdown, parent, false)

        val category = categories[position]
        val textView = dropDownView.findViewById<TextView>(R.id.categoryName)
        textView.text = category.strCategory

        return dropDownView
    }
}
