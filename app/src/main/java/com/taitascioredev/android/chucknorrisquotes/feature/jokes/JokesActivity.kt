package com.taitascioredev.android.chucknorrisquotes.feature.jokes

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.taitascioredev.android.chucknorrisquotes.R
import com.taitascioredev.android.chucknorrisquotes.app
import com.taitascioredev.android.chucknorrisquotes.enableUpNavigation
import com.taitascioredev.android.chucknorrisquotes.log
import com.taitascioredev.android.chucknorrisquotes.model.Joke
import com.taitascioredev.android.chucknorrisquotes.mvibase.MviView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_categories.*
import javax.inject.Inject

/**
 * Created by rrtatasciore on 27/12/17.
 */
class JokesActivity : AppCompatActivity(), MviView<JokesIntent, JokesViewState> {

    lateinit var query: String

    @Inject lateinit var factory: JokesViewModelFactory

    lateinit var viewModel: JokesViewModel

    lateinit var adapter: JokeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes)
        app.component.jokeComponent().inject(this)
        query = intent.getStringExtra("query")
        enableUpNavigation()
        supportActionBar?.title = "Results for '$query'"
        bind()
    }

    fun bind() {
        viewModel = ViewModelProviders.of(this, factory).get(JokesViewModel::class.java)
        viewModel.processIntents(intents())
        viewModel.states().observe(this, Observer { render(it) })
    }

    fun initialIntent(): Observable<JokesIntent> = Observable.just(JokesIntent.InitialIntent.create(query))

    override fun intents(): Observable<JokesIntent> {
        return initialIntent()
    }

    override fun render(viewState: JokesViewState?) {
        when {
            viewState!!.loading() -> renderLoading()
            viewState.jokes() != null -> renderJokes(viewState.jokes()!!)
            viewState.error() != null -> renderError(viewState.error()!!)
        }
    }

    fun renderLoading() {
        log("rendering loading state")
        progress_wheel.visibility = View.VISIBLE
        list.visibility = View.VISIBLE
    }

    fun renderJokes(jokes: List<Joke>) {
        log("rendering jokes")
        progress_wheel.visibility = View.GONE
        list.visibility = View.VISIBLE
        adapter = JokeAdapter(jokes)
        list.adapter = adapter
    }

    fun renderError(error: Throwable) {
        log("rendering error: " + error)
        progress_wheel.visibility = View.GONE
        list.visibility = View.GONE
    }
}