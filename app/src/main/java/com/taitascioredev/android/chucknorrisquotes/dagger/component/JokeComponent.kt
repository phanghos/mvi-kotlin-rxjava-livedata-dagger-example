package com.taitascioredev.android.chucknorrisquotes.dagger.component

import com.taitascioredev.android.chucknorrisquotes.dagger.ActivityScope
import com.taitascioredev.android.chucknorrisquotes.dagger.module.JokeModule
import com.taitascioredev.android.chucknorrisquotes.feature.jokes.JokesActivity
import com.taitascioredev.android.chucknorrisquotes.feature.randomjoke.JokeActivity
import dagger.Subcomponent

/**
 * Created by rrtatasciore on 27/12/17.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(JokeModule::class))
interface JokeComponent {
    fun inject(jokeActivity: JokeActivity)
    fun inject(jokesActivity: JokesActivity)
}