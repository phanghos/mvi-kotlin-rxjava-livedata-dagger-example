package com.taitascioredev.android.chucknorrisquotes.feature.randomjoke

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.taitascioredev.android.chucknorrisquotes.R
import com.taitascioredev.android.chucknorrisquotes.app
import com.taitascioredev.android.chucknorrisquotes.feature.categories.CategoriesActivity
import com.taitascioredev.android.chucknorrisquotes.log
import com.taitascioredev.android.chucknorrisquotes.model.Joke
import com.taitascioredev.android.chucknorrisquotes.mvibase.MviView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_jokes.*
import javax.inject.Inject

class JokeActivity : AppCompatActivity(), MviView<JokeIntent, JokeViewState> {

    @Inject lateinit var factory: JokeViewModelFactory

    lateinit var viewModel: JokeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes)
        app.component.inject(this)
        bind()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        launchCategoriesActivity()
        return true
    }

    fun launchCategoriesActivity() {
        startActivity(Intent(this, CategoriesActivity::class.java))
    }

    fun bind() {
        viewModel = ViewModelProviders.of(this, factory).get(JokeViewModel::class.java)
        viewModel.processIntents(intents())
        viewModel.states().observe(this, Observer { render(it) })
    }

    fun loadIntent() = Observable.just(JokeIntent.LoadIntent.create())

    fun loadNextIntent() = RxView.clicks(btn_next).map { JokeIntent.LoadNextIntent.create() }

    override fun intents(): Observable<JokeIntent> {
        return Observable.merge(loadIntent(), loadNextIntent())
    }

    override fun render(viewState: JokeViewState?) {
        when {
            viewState!!.loading() -> renderLoading()
            viewState.joke() != null -> renderJoke(viewState.joke()!!)
            viewState.error() != null -> renderError(viewState.error()!!)
        }
    }

    private fun renderLoading() {
        log("rendering loading state")
        progress_wheel.visibility = View.VISIBLE
        tv_joke.visibility = View.GONE
        btn_next.visibility = View.GONE
    }

    private fun renderJoke(joke: Joke) {
        log("rendering joke: " + joke.id)
        tv_joke.text = joke.value
        tv_joke.visibility = View.VISIBLE
        progress_wheel.visibility = View.GONE
        btn_next.visibility = View.VISIBLE
        btn_next.text = "next joke"
    }

    private fun renderError(error: Throwable) {
        log("rendering error state: " + error.message)
        progress_wheel.visibility = View.GONE
        tv_joke.visibility = View.GONE
        btn_next.visibility = View.VISIBLE
        btn_next.text = "retry"
        log(error.message!!)
    }
}
