package com.taitascioredev.android.chucknorrisquotes.data.repository.datasource

import com.taitascioredev.android.chucknorrisquotes.model.Categories
import io.reactivex.Single

/**
 * Created by rrtatasciore on 1/01/18.
 */
interface CategoryDataStore {
    fun getCategories(): Single<Categories>
}