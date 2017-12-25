package com.taitascioredev.android.chucknorrisquotes.feature.categories

import com.taitascioredev.android.chucknorrisquotes.LceStatus
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * Created by rrtatasciore on 25/12/17.
 */
class CategoryStateReducer @Inject constructor() : BiFunction<CategoryViewState, CategoryResult, CategoryViewState> {
    override fun apply(state: CategoryViewState, result: CategoryResult): CategoryViewState {
        return when (result.status()) {
            LceStatus.SUCCESS -> CategoryViewState.create(false, result.categories(), null)
            LceStatus.ERROR -> CategoryViewState.create(false, null, result.error())
            else -> CategoryViewState.create(true, null, null)
        }
    }
}