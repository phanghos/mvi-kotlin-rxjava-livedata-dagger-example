package com.taitascioredev.android.chucknorrisquotes.data.repository.datasource

import com.taitascioredev.android.chucknorrisquotes.data.db.ChuckNorrisDb
import com.taitascioredev.android.chucknorrisquotes.model.Categories
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by rrtatasciore on 1/01/18.
 */
class DiskCategoryDataStore @Inject constructor(private val db: ChuckNorrisDb) : CategoryDataStore {
    override fun getCategories(): Single<Categories> {
        return Single.just(db.categoryDao().getCategories())
    }
}