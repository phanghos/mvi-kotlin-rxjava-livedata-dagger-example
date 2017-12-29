package com.taitascioredev.android.chucknorrisquotes.feature.randomjoke

import com.taitascioredev.android.chucknorrisquotes.data.repository.JokeRepository
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by rrtatasciore on 24/12/17.
 */
data class
JokeActionProcessor @Inject constructor(private val repository: JokeRepository) {

    private fun loadJoke(): ObservableTransformer<JokeAction.LoadJokeAction, JokeResult.LoadJokeResult> {
        return ObservableTransformer { action ->
            action.flatMap {
                repository.getRandomJoke(it.category())
                        .map { JokeResult.LoadJokeResult.success(it) }
                        .onErrorReturn { JokeResult.LoadJokeResult.error(it) }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .startWith(JokeResult.LoadJokeResult.inFlight())
            }
        }
    }

    fun transformerFromAction(): ObservableTransformer<JokeAction, JokeResult.LoadJokeResult> {
        return ObservableTransformer { action ->
            action.publish { shared ->
                shared.ofType(JokeAction.LoadJokeAction::class.java).compose(loadJoke())
            }
        }
    }
}