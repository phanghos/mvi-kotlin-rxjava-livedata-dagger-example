package com.taitascioredev.android.chucknorrisquotes.feature.jokes

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Created by rrtatasciore on 27/12/17.
 */
data class JokesViewModelFactory @Inject constructor(
        private val actionProcessor: JokesActionProcessor,
        private val stateReducer: JokesStateReducer) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return JokesViewModel(actionProcessor, stateReducer) as T
    }
}