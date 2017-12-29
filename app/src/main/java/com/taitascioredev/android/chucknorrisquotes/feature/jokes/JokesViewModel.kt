package com.taitascioredev.android.chucknorrisquotes.feature.jokes

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.taitascioredev.android.chucknorrisquotes.mvibase.MviViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

/**
 * Created by rrtatasciore on 27/12/17.
 */
data class JokesViewModel(
        private val actionProcessor: JokesActionProcessor,
        private val stateReducer: JokesStateReducer) : ViewModel(), MviViewModel<JokesIntent, JokesViewState> {

    private val intentsSubject: PublishSubject<JokesIntent>

    private val statesObservable: Observable<JokesViewState>

    private val statesLiveData: MutableLiveData<JokesViewState>

    private val disposables: CompositeDisposable

    private val intentFilter = ObservableTransformer<JokesIntent, JokesIntent> { intent ->
        intent.publish { shared ->
            Observable.merge(
                    shared.ofType(JokesIntent.InitialIntent::class.java).take(1),
                    shared.filter { it !is JokesIntent.InitialIntent }
            )
        }
    }

    init {
        intentsSubject = PublishSubject.create()
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

    override fun processIntents(intents: Observable<JokesIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): LiveData<JokesViewState> {
        return statesLiveData
    }

    private fun actionFromIntent(intent: JokesIntent): JokesAction {
        return when (intent) {
            is JokesIntent.InitialIntent -> JokesAction.create(intent.category())
            is JokesIntent.LoadJokesIntent -> JokesAction.create(intent.category())
            else -> throw IllegalArgumentException()
        }
    }

    private fun compose(): Observable<JokesViewState> {
        return intentsSubject
                .compose(intentFilter)
                .map { actionFromIntent(it) }
                .compose(actionProcessor.transformerFromAction())
                .scan(JokesViewState.idle(), stateReducer)
                .replay(1).autoConnect(0)
    }
}