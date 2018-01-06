package com.taitascioredev.android.chucknorrisquotes.dagger.module

import com.taitascioredev.android.chucknorrisquotes.dagger.ActivityScope
import com.taitascioredev.android.chucknorrisquotes.data.repository.JokeRepository
import com.taitascioredev.android.chucknorrisquotes.feature.randomjoke.RandomJokeActionProcessor
import com.taitascioredev.android.chucknorrisquotes.feature.randomjoke.RandomJokeStateReducer
import com.taitascioredev.android.chucknorrisquotes.feature.randomjoke.RandomJokeViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Created by rrtatasciore on 1/01/18.
 */
@Module(includes = arrayOf(DataModule::class))
class RandomJokeModule {

    @Provides
    @ActivityScope
    fun provideJokeViewModelFactory(actionProcessor: RandomJokeActionProcessor, stateReducer: RandomJokeStateReducer): RandomJokeViewModelFactory {
        return RandomJokeViewModelFactory(actionProcessor, stateReducer)
    }

    @Provides
    @ActivityScope
    fun provideJokeActionProcessor(repository: JokeRepository): RandomJokeActionProcessor = RandomJokeActionProcessor(repository)

    @Provides
    @ActivityScope
    fun provideJokeStateReducer(): RandomJokeStateReducer = RandomJokeStateReducer()
}