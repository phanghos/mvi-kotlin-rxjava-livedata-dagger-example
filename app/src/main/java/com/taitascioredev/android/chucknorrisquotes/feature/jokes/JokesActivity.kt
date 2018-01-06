package com.taitascioredev.android.chucknorrisquotes.feature.jokes

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.taitascioredev.android.chucknorrisquotes.R
import com.taitascioredev.android.chucknorrisquotes.enableUpNavigation
import com.taitascioredev.android.chucknorrisquotes.log
import com.taitascioredev.android.chucknorrisquotes.feature.randomjoke.Joke
import com.taitascioredev.android.chucknorrisquotes.mvibase.MviView
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_jokes.*
import javax.inject.Inject

/**
 * Created by rrtatasciore on 27/12/17.
 */
class JokesActivity : AppCompatActivity(), MviView<JokesIntent, JokesViewState>, HasActivityInjector {

    override fun activityInjector(): AndroidInjector<Activity> {
        return injector
    }

    lateinit var query: String

    @Inject lateinit var factory: JokesViewModelFactory

    @Inject lateinit var injector: DispatchingAndroidInjector<Activity>

    lateinit var viewModel: JokesViewModel

    lateinit var adapter: JokeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes)
        AndroidInjection.inject(this)
        query = intent.getStringExtra("query")
        enableUpNavigation()
        supportActionBar?.title = "Results for '$query'"
        bind()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> onOptionsItemSelected(item)
        }
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
        viewState?.let {
            when {
                viewState.loading() -> renderLoading()
                viewState.jokes() != null -> {
                    if (viewState.jokes()!!.isNotEmpty()) renderJokes(viewState.jokes()!!)
                    else renderEmpty()
                }
                viewState.error() != null -> renderError(viewState.error()!!)
            }
        }
    }

    fun renderLoading() {
        log("rendering loading state")
        progress_wheel.visibility = View.VISIBLE
        list.visibility = View.VISIBLE
        tv_empty.visibility = View.GONE
    }

    fun renderJokes(jokes: List<Joke>) {
        log("rendering jokes")
        progress_wheel.visibility = View.GONE
        list.visibility = View.VISIBLE
        adapter = JokeAdapter(jokes, query)
        list.adapter = adapter
        tv_empty.visibility = View.GONE
    }

    fun renderEmpty() {
        log("rendering empty state")
        progress_wheel.visibility = View.GONE
        list.visibility = View.GONE
        list.visibility = View.GONE
        tv_empty.visibility = View.VISIBLE
        tv_empty.text = "No jokes matching '$query' were found"
    }

    fun renderError(error: Throwable) {
        log("rendering error state: " + error)
        progress_wheel.visibility = View.GONE
        list.visibility = View.GONE
    }
}