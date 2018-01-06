package com.taitascioredev.android.chucknorrisquotes.data.repository.datasource

import com.taitascioredev.android.chucknorrisquotes.data.db.ChuckNorrisDb
import com.taitascioredev.android.chucknorrisquotes.data.net.ChuckNorrisService
import com.taitascioredev.android.chucknorrisquotes.feature.categories.Categories
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by rrtatasciore on 1/01/18.
 */
class CloudCategoryDataStore @Inject constructor(
        private val service: ChuckNorrisService,
        private val db: ChuckNorrisDb) : CategoryDataStore {
    override fun getCategories(): Single<Categories> {
        return service.getCategories().singleOrError()
                .map { Categories(it) }
                .doOnSuccess { db.categoryDao().insertCategories(it) }
    }
}