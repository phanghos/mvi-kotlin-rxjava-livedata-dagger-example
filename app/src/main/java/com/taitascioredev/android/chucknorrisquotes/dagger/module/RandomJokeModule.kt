package com.taitascioredev.android.chucknorrisquotes.dagger.module

import com.taitascioredev.android.chucknorrisquotes.dagger.ActivityScope
import com.taitascioredev.android.chucknorrisquotes.data.repository.JokeRepository
import com.taitascioredev.android.chucknorrisquotes.feature.randomjoke.JokeActionProcessor
import com.taitascioredev.android.chucknorrisquotes.feature.randomjoke.JokeStateReducer
import com.taitascioredev.android.chucknorrisquotes.feature.randomjoke.JokeViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Created by rrtatasciore on 1/01/18.
 */
@Module(includes = arrayOf(DataModule::class))
class RandomJokeModule {

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
}