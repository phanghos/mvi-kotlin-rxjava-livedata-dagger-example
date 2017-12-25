package com.taitascioredev.android.chucknorrisquotes.feature.categories

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Created by rrtatasciore on 25/12/17.
 */
data class CategoryViewModelFactory @Inject constructor(
        private val actionProcessor: CategoryActionProcessor,
        private val stateReducer: CategoryStateReducer) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoryViewModel(actionProcessor, stateReducer) as T
    }
}