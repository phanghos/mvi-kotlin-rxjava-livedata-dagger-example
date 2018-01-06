package com.taitascioredev.android.chucknorrisquotes.feature.randomjoke

import com.taitascioredev.android.chucknorrisquotes.LceStatus
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * Created by rrtatasciore on 24/12/17.
 */
class RandomJokeStateReducer @Inject constructor() : BiFunction<RandomJokeViewState, RandomJokeResult.LoadJokeResult, RandomJokeViewState> {
    override fun apply(state: RandomJokeViewState, result: RandomJokeResult.LoadJokeResult): RandomJokeViewState {
        return when (result.status()) {
            LceStatus.SUCCESS -> RandomJokeViewState.create(false, result.joke(), null)
            LceStatus.ERROR -> RandomJokeViewState.create(false, null, result.error())
            else -> RandomJokeViewState.create(true, null, null)
        }
    }
}