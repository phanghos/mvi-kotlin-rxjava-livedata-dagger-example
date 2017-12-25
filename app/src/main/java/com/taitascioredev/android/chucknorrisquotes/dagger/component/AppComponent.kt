package com.taitascioredev.android.chucknorrisquotes.dagger.component

import com.taitascioredev.android.chucknorrisquotes.ChuckNorrisApp
import com.taitascioredev.android.chucknorrisquotes.dagger.module.DataModule
import com.taitascioredev.android.chucknorrisquotes.dagger.module.JokeModule
import com.taitascioredev.android.chucknorrisquotes.dagger.module.NetModule
import com.taitascioredev.android.chucknorrisquotes.feature.randomjoke.JokeActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by rrtatasciore on 24/12/17.
 */
@Singleton
@Component(modules = arrayOf(NetModule::class, DataModule::class, JokeModule::class))
interface AppComponent {
    fun inject(chuckNorrisApp: ChuckNorrisApp)
    fun inject(jokeActivity: JokeActivity)
    fun categoryComponent(): CategoryComponent
}