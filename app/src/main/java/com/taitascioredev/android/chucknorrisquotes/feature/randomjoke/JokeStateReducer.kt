package com.taitascioredev.android.chucknorrisquotes.feature.randomjoke

import com.taitascioredev.android.chucknorrisquotes.LceStatus
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * Created by rrtatasciore on 24/12/17.
 */
class JokeStateReducer @Inject constructor() : BiFunction<JokeViewState, JokeResult.LoadJokeResult, JokeViewState> {
    override fun apply(state: JokeViewState, result: JokeResult.LoadJokeResult): JokeViewState {
        return when (result.status()) {
            LceStatus.SUCCESS -> JokeViewState.create(false, result.joke(), null)
            LceStatus.ERROR -> JokeViewState.create(false, null, result.error())
            else -> JokeViewState.create(true, null, null)
        }
    }
}