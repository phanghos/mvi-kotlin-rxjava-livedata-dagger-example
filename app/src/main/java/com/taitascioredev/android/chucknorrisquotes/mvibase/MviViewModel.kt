package com.taitascioredev.android.chucknorrisquotes.mvibase

import android.arch.lifecycle.LiveData
import io.reactivex.Observable

/**
 * Created by rrtatasciore on 24/12/17.
 */
interface MviViewModel<I : MviIntent, VS : MviViewState> {
    fun processIntents(intents: Observable<I>)
    fun states(): LiveData<VS>
}