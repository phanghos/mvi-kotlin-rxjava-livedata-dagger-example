package com.taitascioredev.android.chucknorrisquotes.data.repository.datasource

import com.taitascioredev.android.chucknorrisquotes.data.db.ChuckNorrisDb
import com.taitascioredev.android.chucknorrisquotes.data.net.ChuckNorrisService
import com.taitascioredev.android.chucknorrisquotes.model.Categories
import javax.inject.Inject

/**
 * Created by rrtatasciore on 1/01/18.
 */
class CategoryDataStoreFactory @Inject constructor(
        private val service: ChuckNorrisService,
        private val db: ChuckNorrisDb
) {

    fun create(): CategoryDataStore {
        val categories: Categories? = db.categoryDao().getCategories()
        if (categories != null && categories?.list != null && categories!!.list!!.isNotEmpty()) {
            return DiskCategoryDataStore(db)
        } else {
            return CloudCategoryDataStore(service, db)
        }
    }
}