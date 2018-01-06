package com.taitascioredev.android.chucknorrisquotes.data.repository.datasource

import com.taitascioredev.android.chucknorrisquotes.feature.categories.Categories
import io.reactivex.Single

/**
 * Created by rrtatasciore on 1/01/18.
 */
interface CategoryDataStore {
    fun getCategories(): Single<Categories>
}