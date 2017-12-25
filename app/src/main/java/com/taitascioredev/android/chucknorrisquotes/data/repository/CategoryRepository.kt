package com.taitascioredev.android.chucknorrisquotes.data.repository

import io.reactivex.Observable

/**
 * Created by rrtatasciore on 25/12/17.
 */
interface CategoryRepository {
    fun getCategories(): Observable<List<String>>
}