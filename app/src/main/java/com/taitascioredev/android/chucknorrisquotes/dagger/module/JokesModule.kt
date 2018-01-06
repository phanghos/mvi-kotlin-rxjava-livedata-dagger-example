package com.taitascioredev.android.chucknorrisquotes.dagger.module

import com.taitascioredev.android.chucknorrisquotes.dagger.ActivityScope
import com.taitascioredev.android.chucknorrisquotes.data.repository.JokeRepository
import com.taitascioredev.android.chucknorrisquotes.feature.jokes.JokesActionProcessor
import com.taitascioredev.android.chucknorrisquotes.feature.jokes.JokesStateReducer
import com.taitascioredev.android.chucknorrisquotes.feature.jokes.JokesViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Created by rrtatasciore on 24/12/17.
 */
@Module(includes = arrayOf(DataModule::class))
class JokesModule {

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