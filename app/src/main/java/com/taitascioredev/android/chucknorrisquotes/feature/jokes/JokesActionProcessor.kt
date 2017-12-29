package com.taitascioredev.android.chucknorrisquotes.feature.jokes

import com.taitascioredev.android.chucknorrisquotes.LceStatus
import com.taitascioredev.android.chucknorrisquotes.data.repository.JokeRepository
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by rrtatasciore on 27/12/17.
 */
data class JokesActionProcessor @Inject constructor(private val repository: JokeRepository) {

    private fun loadJokes(): ObservableTransformer<JokesAction, JokesResult> {
        return ObservableTransformer { action ->
            action.flatMap {
                repository.queryJokes(it.category())
                        .map { JokesResult.create(LceStatus.SUCCESS, it.result, null) }
                        .onErrorReturn { JokesResult.create(LceStatus.ERROR, null, it) }
                        .startWith(JokesResult.inFlight())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }

    fun transformerFromAction(): ObservableTransformer<JokesAction, JokesResult> {
        return ObservableTransformer { action ->
            action.publish { shared ->
                shared.ofType(JokesAction::class.java).compose(loadJokes())
            }
        }
    }
}