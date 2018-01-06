package com.taitascioredev.android.chucknorrisquotes.feature.randomjoke

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Created by rrtatasciore on 24/12/17.
 */
data class RandomJokeViewModelFactory @Inject constructor(
        private val actionProcessor: RandomJokeActionProcessor,
        private val stateReducer: RandomJokeStateReducer) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RandomJokeViewModel(actionProcessor, stateReducer) as T
    }
}