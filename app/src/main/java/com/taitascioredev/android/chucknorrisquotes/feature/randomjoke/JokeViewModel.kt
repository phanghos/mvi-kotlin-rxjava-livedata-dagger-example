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
data class JokeViewModel(
        private val actionProcessor: JokeActionProcessor,
        private val stateReducer: JokeStateReducer) : ViewModel(), MviViewModel<JokeIntent, JokeViewState> {

    private val intentsSubject: BehaviorSubject<JokeIntent>

    private val statesObservable: Observable<JokeViewState>

    private val statesLiveData: MutableLiveData<JokeViewState>

    private val disposables: CompositeDisposable

    private val intentFilter = ObservableTransformer<JokeIntent, JokeIntent> { intent ->
        intent.publish { shared ->
            Observable.merge(
                    shared.ofType(JokeIntent.LoadIntent::class.java).take(1),
                    shared.filter { it !is JokeIntent.LoadIntent }
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

    override fun processIntents(intents: Observable<JokeIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): LiveData<JokeViewState> {
        return statesLiveData
    }

    private fun actionFromIntent(intent: JokeIntent): JokeAction {
        return when (intent) {
            is JokeIntent.LoadIntent -> JokeAction.LoadJokeAction.create(intent.category())
            is JokeIntent.LoadNextIntent -> JokeAction.LoadNextJokeAction.create(intent.category())
            else -> throw IllegalArgumentException("unknown intent")
        }
    }

    private fun compose(): Observable<JokeViewState> {
        return intentsSubject
                .compose(intentFilter)
                .map { actionFromIntent(it) }
                .compose(actionProcessor.transformerFromAction())
                .scan(JokeViewState.idle(), stateReducer)
                .replay(1).autoConnect(0)
    }
}