package com.taitascioredev.android.chucknorrisquotes.feature.randomjoke

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.taitascioredev.android.chucknorrisquotes.mvibase.MviViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import java.lang.IllegalArgumentException

/**
 * Created by rrtatasciore on 24/12/17.
 */
data class RandomJokeViewModel(
        private val actionProcessor: RandomJokeActionProcessor,
        private val stateReducer: RandomJokeStateReducer) : ViewModel(), MviViewModel<RandomJokeIntent, RandomJokeViewState> {

    private val intentsSubject: BehaviorSubject<RandomJokeIntent>

    private val statesObservable: Observable<RandomJokeViewState>

    private val statesLiveData: MutableLiveData<RandomJokeViewState>

    private val disposables: CompositeDisposable

    private val intentFilter = ObservableTransformer<RandomJokeIntent, RandomJokeIntent> { intent ->
        intent.publish { shared ->
            Observable.merge(
                    shared.ofType(RandomJokeIntent.LoadIntent::class.java).take(1),
                    shared.filter { it !is RandomJokeIntent.LoadIntent }
            )
        }
    }

    init {
        intentsSubject = BehaviorSubject.create()
        statesObservable = compose()
        statesLiveData = MutableLiveData()
        disposables = CompositeDisposable()
        disposables.add(statesObservable.subscribe { statesLiveData.value = it })
    }

    override fun onCleared() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    override fun processIntents(intents: Observable<RandomJokeIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): LiveData<RandomJokeViewState> {
        return statesLiveData
    }

    private fun actionFromIntent(intent: RandomJokeIntent): RandomJokeAction {
        return when (intent) {
            is RandomJokeIntent.LoadIntent -> RandomJokeAction.LoadJokeAction.create(intent.category())
            is RandomJokeIntent.LoadNextIntent -> RandomJokeAction.LoadJokeAction.create(intent.category())
            else -> throw IllegalArgumentException("unknown intent")
        }
    }

    private fun compose(): Observable<RandomJokeViewState> {
        return intentsSubject
                .compose(intentFilter)
                .map { actionFromIntent(it) }
                .compose(actionProcessor.transformerFromAction())
                .scan(RandomJokeViewState.idle(), stateReducer)
                .replay(1).autoConnect(0)
    }
}