package com.taitascioredev.android.chucknorrisquotes.feature.categories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.taitascioredev.android.chucknorrisquotes.mvibase.MviViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import java.lang.IllegalArgumentException
import javax.inject.Inject

/**
 * Created by rrtatasciore on 25/12/17.
 */
data class CategoryViewModel @Inject constructor(
        private val actionProcessor: CategoryActionProcessor,
        private val stateReducer: CategoryStateReducer) : ViewModel(), MviViewModel<CategoryIntent, CategoryViewState> {

    private val intentSubjects: BehaviorSubject<CategoryIntent>

    private val viewStateObservable: Observable<CategoryViewState>

    private val statesObservable: MutableLiveData<CategoryViewState>

    private val disposables: CompositeDisposable

    private val intentFilter = ObservableTransformer<CategoryIntent, CategoryIntent> { intent ->
        intent.publish { shared ->
            Observable.merge(
                    shared.ofType(CategoryIntent.InitialIntent::class.java).take(1),
                    shared.filter { it !is CategoryIntent.InitialIntent }
            )
        }
    }

    init {
        intentSubjects = BehaviorSubject.create()
        viewStateObservable = compose()
        statesObservable = MutableLiveData()
        disposables = CompositeDisposable()
        disposables.add(viewStateObservable.subscribe { statesObservable.value = it })
    }

    override fun onCleared() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    override fun processIntents(intents: Observable<CategoryIntent>) {
        intents.subscribe(intentSubjects)
    }

    override fun states(): LiveData<CategoryViewState> {
        return statesObservable
    }

    private fun actionFromIntent(intent: CategoryIntent): CategoryAction {
        return when (intent) {
        //is CategoryIntent.InitialIntent -> CategoryAction.LoadCategoriesAction.create()
            is CategoryIntent.LoadIntent -> CategoryAction.LoadCategoriesAction.create()
            else -> throw IllegalArgumentException("unknown intent")
        }
    }

    private fun compose(): Observable<CategoryViewState> {
        return intentSubjects
                //.compose(intentFilter)
                .map { actionFromIntent(it) }
                .compose(actionProcessor.transformerFromAction())
                .scan(CategoryViewState.idle(), stateReducer)
                .replay(1).autoConnect(0)
    }
}