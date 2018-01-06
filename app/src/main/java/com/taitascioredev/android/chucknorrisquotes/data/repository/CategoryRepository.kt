package com.taitascioredev.android.chucknorrisquotes.data.repository

import com.taitascioredev.android.chucknorrisquotes.feature.categories.Categories
import io.reactivex.Single

/**
 * Created by rrtatasciore on 25/12/17.
 */
interface CategoryRepository {
    fun getCategories(): Single<Categories>
}