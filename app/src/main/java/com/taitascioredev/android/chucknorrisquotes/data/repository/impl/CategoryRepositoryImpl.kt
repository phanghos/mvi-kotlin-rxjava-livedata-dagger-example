package com.taitascioredev.android.chucknorrisquotes.data.repository.impl

import com.taitascioredev.android.chucknorrisquotes.data.repository.CategoryRepository
import com.taitascioredev.android.chucknorrisquotes.data.repository.datasource.CategoryDataStoreFactory
import com.taitascioredev.android.chucknorrisquotes.feature.categories.Categories
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by rrtatasciore on 25/12/17.
 */
class CategoryRepositoryImpl @Inject constructor(private val dataStoreFactory: CategoryDataStoreFactory) : CategoryRepository {
    override fun getCategories(): Single<Categories> {
        return dataStoreFactory.create().getCategories()
    }
}