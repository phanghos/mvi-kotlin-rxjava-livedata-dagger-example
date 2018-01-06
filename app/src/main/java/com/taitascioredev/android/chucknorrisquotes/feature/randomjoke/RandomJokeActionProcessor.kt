package com.taitascioredev.android.chucknorrisquotes.feature.randomjoke

import com.taitascioredev.android.chucknorrisquotes.data.repository.JokeRepository
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by rrtatasciore on 24/12/17.
 */
class RandomJokeActionProcessor @Inject constructor(private val repository: JokeRepository) {

    private fun loadJoke(): ObservableTransformer<RandomJokeAction.LoadJokeAction, RandomJokeResult.LoadJokeResult> {
        return ObservableTransformer { action ->
            action.flatMap {
                repository.getRandomJoke(it.category())
                        .map { RandomJokeResult.LoadJokeResult.success(it) }
                        .onErrorReturn { RandomJokeResult.LoadJokeResult.error(it) }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .startWith(RandomJokeResult.LoadJokeResult.inFlight())
            }
        }
    }

    fun transformerFromAction(): ObservableTransformer<RandomJokeAction, RandomJokeResult.LoadJokeResult> {
        return ObservableTransformer { action ->
            action.publish { shared ->
                shared.ofType(RandomJokeAction.LoadJokeAction::class.java).compose(loadJoke())
            }
        }
    }
}