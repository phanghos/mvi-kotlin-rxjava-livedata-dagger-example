package com.taitascioredev.android.chucknorrisquotes.feature.categories

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.taitascioredev.android.chucknorrisquotes.R
import com.taitascioredev.android.chucknorrisquotes.app
import com.taitascioredev.android.chucknorrisquotes.enableUpNavigation
import com.taitascioredev.android.chucknorrisquotes.feature.randomjoke.JokeActivity
import com.taitascioredev.android.chucknorrisquotes.log
import com.taitascioredev.android.chucknorrisquotes.mvibase.MviView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_categories.*
import javax.inject.Inject

/**
 * Created by rrtatasciore on 25/12/17.
 */
class CategoriesActivity : AppCompatActivity(), MviView<CategoryIntent, CategoryViewState> {

    @Inject lateinit var factory: CategoryViewModelFactory

    lateinit var viewModel: CategoryViewModel

    lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        app.component.categoryComponent().inject(this)
        enableUpNavigation()
        supportActionBar?.title = "Categories"
        bind()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun bind() {
        viewModel = ViewModelProviders.of(this, factory).get(CategoryViewModel::class.java)
        viewModel.processIntents(intents())
        viewModel.states().observe(this, Observer { render(it) })
    }

    fun loadIntent(): Observable<CategoryIntent> = Observable.just(CategoryIntent.LoadIntent.create())

    override fun intents(): Observable<CategoryIntent> {
        return loadIntent()
    }

    override fun render(viewState: CategoryViewState?) {
        when {
            viewState!!.loading() -> renderLoading()
            viewState.categories() != null -> renderCategories(viewState.categories()!!)
            viewState.error() != null -> renderError(viewState.error()!!)
        }
    }

    fun renderLoading() {
        log("rendering load state")
        progress_wheel.visibility = View.VISIBLE
        list.visibility = View.GONE
    }

    fun renderCategories(categories: List<String>) {
        log("rendering categories")
        progress_wheel.visibility = View.GONE
        list.visibility = View.VISIBLE
        adapter = CategoryAdapter(categories)
        adapter.clickObservable().subscribe { launchCategoryJokesActivity(it) }
        list.adapter = adapter
    }

    fun renderError(error: Throwable) {
        log("rendering error")
        progress_wheel.visibility = View.GONE
        list.visibility = View.GONE
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }

    fun launchCategoryJokesActivity(category: String) {
        log("selected query " + category)
        val intent = Intent(this, JokeActivity::class.java)
        intent.putExtra("query", category)
        startActivity(intent)
    }
}