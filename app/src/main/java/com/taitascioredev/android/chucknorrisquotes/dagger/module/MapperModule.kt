package com.taitascioredev.android.chucknorrisquotes.dagger.module

import com.taitascioredev.android.chucknorrisquotes.data.entity.JokeMapper
import com.taitascioredev.android.chucknorrisquotes.data.entity.JokeQueryMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by rrtatasciore on 1/01/18.
 */
@Module
class MapperModule {

    @Provides
    @Singleton
    fun provideJokeMapper(): JokeMapper = JokeMapper()

    @Provides
    @Singleton
    fun provideJokeQueryMapper(): JokeQueryMapper = JokeQueryMapper()
}