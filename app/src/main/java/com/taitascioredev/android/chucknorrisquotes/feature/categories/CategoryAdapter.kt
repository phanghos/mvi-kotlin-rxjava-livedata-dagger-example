package com.taitascioredev.android.chucknorrisquotes.feature.categories

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.taitascioredev.android.chucknorrisquotes.R

/**
 * Created by rrtatasciore on 25/12/17.
 */
data class CategoryAdapter(private val categories: List<String>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CategoryViewHolder {
        val v = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.category_row_layout, parent, false)
        return CategoryViewHolder(v)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder?, position: Int) {
        val category = categories.get(position)
        holder!!.category.text = category
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val category: TextView = view.findViewById(R.id.tv_category)
    }
}