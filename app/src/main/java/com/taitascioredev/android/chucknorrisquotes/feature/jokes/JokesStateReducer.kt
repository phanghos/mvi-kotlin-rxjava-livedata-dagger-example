package com.taitascioredev.android.chucknorrisquotes.feature.jokes

import com.taitascioredev.android.chucknorrisquotes.LceStatus
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * Created by rrtatasciore on 27/12/17.
 */
class JokesStateReducer @Inject constructor() : BiFunction<JokesViewState, JokesResult, JokesViewState> {
    override fun apply(state: JokesViewState, result: JokesResult): JokesViewState {
        return when(result.status()) {
            LceStatus.SUCCESS -> JokesViewState.success(result.jokes()!!)
            LceStatus.ERROR -> JokesViewState.error(result.error()!!)
            else -> JokesViewState.inFlight()
        }
    }
}