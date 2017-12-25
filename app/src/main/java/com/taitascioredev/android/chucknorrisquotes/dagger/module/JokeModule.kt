package com.taitascioredev.android.chucknorrisquotes.dagger.module

import com.taitascioredev.android.chucknorrisquotes.data.repository.JokeRepository
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
    @Singleton
    fun provideJokeViewModelFactory(actionProcessor: JokeActionProcessor, stateReducer: JokeStateReducer): JokeViewModelFactory {
        return JokeViewModelFactory(actionProcessor, stateReducer)
    }

    @Provides
    @Singleton
    fun provideJokeActionProcessor(repository: JokeRepository): JokeActionProcessor = JokeActionProcessor(repository)

    @Provides
    @Singleton
    fun provideJokeStateReducer(): JokeStateReducer = JokeStateReducer()
}