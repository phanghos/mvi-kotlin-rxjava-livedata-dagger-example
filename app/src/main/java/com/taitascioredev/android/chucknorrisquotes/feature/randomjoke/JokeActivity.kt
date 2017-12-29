package com.taitascioredev.android.chucknorrisquotes.feature.randomjoke

import android.app.SearchManager
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import com.taitascioredev.android.chucknorrisquotes.R
import com.taitascioredev.android.chucknorrisquotes.app
import com.taitascioredev.android.chucknorrisquotes.enableUpNavigation
import com.taitascioredev.android.chucknorrisquotes.feature.categories.CategoriesActivity
import com.taitascioredev.android.chucknorrisquotes.log
import com.taitascioredev.android.chucknorrisquotes.model.Joke
import com.taitascioredev.android.chucknorrisquotes.mvibase.MviView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_joke.*
import javax.inject.Inject


class JokeActivity : AppCompatActivity(), MviView<JokeIntent, JokeViewState> {

    var category: String? = null

    @Inject lateinit var factory: JokeViewModelFactory

    lateinit var viewModel: JokeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke)
        app.component.jokeComponent().inject(this)
        category = intent.getStringExtra("query")
        setupToolbar()
        bind()
    }

    fun setupToolbar() {
        category ?.let {
            supportActionBar?.title = category
            enableUpNavigation()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        /*
        if (category != null) {
            return false
        }
        */
        menuInflater.inflate(R.menu.main, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu!!.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.item_categories -> {
                launchCategoriesActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun launchCategoriesActivity() {
        startActivity(Intent(this, CategoriesActivity::class.java))
    }

    fun bind() {
        viewModel = ViewModelProviders.of(this, factory).get(JokeViewModel::class.java)
        viewModel.processIntents(intents())
        viewModel.states().observe(this, Observer { render(it) })
    }

    fun loadIntent() = Observable.just(JokeIntent.LoadIntent.create(category))

    fun loadNextIntent() = RxView.clicks(btn_next).map { JokeIntent.LoadNextIntent.create(category) }

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
