package com.taitascioredev.android.chucknorrisquotes.dagger.module

import com.taitascioredev.android.chucknorrisquotes.dagger.ActivityScope
import com.taitascioredev.android.chucknorrisquotes.data.repository.JokeRepository
import com.taitascioredev.android.chucknorrisquotes.feature.jokes.JokesActionProcessor
import com.taitascioredev.android.chucknorrisquotes.feature.jokes.JokesStateReducer
import com.taitascioredev.android.chucknorrisquotes.feature.jokes.JokesViewModelFactory
import com.taitascioredev.android.chucknorrisquotes.feature.randomjoke.JokeActionProcessor
import com.taitascioredev.android.chucknorrisquotes.feature.randomjoke.JokeStateReducer
import com.taitascioredev.android.chucknorrisquotes.feature.randomjoke.JokeViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by rrtatasciore on 24/12/17.
 */
@Module
class JokeModule {

    @Provides
    @ActivityScope
    fun provideJokeViewModelFactory(actionProcessor: JokeActionProcessor, stateReducer: JokeStateReducer): JokeViewModelFactory {
        return JokeViewModelFactory(actionProcessor, stateReducer)
    }

    @Provides
    @ActivityScope
    fun provideJokeActionProcessor(repository: JokeRepository): JokeActionProcessor = JokeActionProcessor(repository)

    @Provides
    @ActivityScope
    fun provideJokeStateReducer(): JokeStateReducer = JokeStateReducer()

    @Provides
    @ActivityScope
    fun provideJokesViewModelFactory(actionProcessor: JokesActionProcessor, stateReducer: JokesStateReducer): JokesViewModelFactory {
        return JokesViewModelFactory(actionProcessor, stateReducer)
    }

    @Provides
    @ActivityScope
    fun provideJokesActionProcessor(repository: JokeRepository): JokesActionProcessor {
        return JokesActionProcessor(repository)
    }

    @Provides
    @ActivityScope
    fun provideJokesStateReducer(): JokesStateReducer {
        return JokesStateReducer()
    }
}