package com.taitascioredev.android.chucknorrisquotes.mvibase

import io.reactivex.Observable

/**
 * Created by rrtatasciore on 24/12/17.
 */
interface MviView<I : MviIntent, VS : MviViewState> {
    fun intents(): Observable<I>
    fun render(viewState: VS?)
}