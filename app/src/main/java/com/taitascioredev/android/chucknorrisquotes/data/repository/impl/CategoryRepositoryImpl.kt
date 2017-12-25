package com.taitascioredev.android.chucknorrisquotes.data.repository.impl

import com.taitascioredev.android.chucknorrisquotes.data.net.ChuckNorrisService
import com.taitascioredev.android.chucknorrisquotes.data.repository.CategoryRepository

/**
 * Created by rrtatasciore on 25/12/17.
 */
data class CategoryRepositoryImpl(private val service: ChuckNorrisService) : CategoryRepository {
    override fun getCategories() = service.getCategories()
}