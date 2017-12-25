package com.taitascioredev.android.chucknorrisquotes.feature.categories

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView
import com.taitascioredev.android.chucknorrisquotes.R
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by rrtatasciore on 25/12/17.
 */
data class CategoryAdapter(private val categories: List<String>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val clickSubject: PublishSubject<String> = PublishSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CategoryViewHolder {
        val v = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.category_row_layout, parent, false)
        return CategoryViewHolder(v)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder?, position: Int) {
        val category = categories[position]
        holder!!.category.text = category
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun clickObservable(): Observable<String> = clickSubject

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val category: TextView = view.findViewById(R.id.tv_category)
        init { RxView.clicks(category).subscribe { clickSubject.onNext(categories[adapterPosition]) } }
    }
}