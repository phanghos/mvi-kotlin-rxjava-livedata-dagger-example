package com.taitascioredev.android.chucknorrisquotes.dagger.component

import com.taitascioredev.android.chucknorrisquotes.ChuckNorrisApp
import com.taitascioredev.android.chucknorrisquotes.dagger.module.ActivityBindingModule
import com.taitascioredev.android.chucknorrisquotes.dagger.module.AppModule
import com.taitascioredev.android.chucknorrisquotes.dagger.module.MapperModule
import com.taitascioredev.android.chucknorrisquotes.dagger.module.NetModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by rrtatasciore on 24/12/17.
 */
@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, AppModule::class, NetModule::class, MapperModule::class, ActivityBindingModule::class))
interface AppComponent : AndroidInjector<ChuckNorrisApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<ChuckNorrisApp>() {
        abstract fun appModule(appModule: AppModule): Builder
        override fun seedInstance(instance: ChuckNorrisApp?) {
            appModule(AppModule(instance!!))
        }
    }
}