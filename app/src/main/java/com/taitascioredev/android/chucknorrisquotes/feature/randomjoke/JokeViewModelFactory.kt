package com.taitascioredev.android.chucknorrisquotes.feature.randomjoke

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Created by rrtatasciore on 24/12/17.
 */
data class JokeViewModelFactory @Inject constructor(
        private val actionProcessor: JokeActionProcessor,
        private val stateReducer: JokeStateReducer) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return JokeViewModel(actionProcessor, stateReducer) as T
    }
}