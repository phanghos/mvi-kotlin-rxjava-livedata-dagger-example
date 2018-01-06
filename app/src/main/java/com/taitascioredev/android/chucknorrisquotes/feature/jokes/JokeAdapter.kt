package com.taitascioredev.android.chucknorrisquotes.feature.jokes

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.taitascioredev.android.chucknorrisquotes.R
import com.taitascioredev.android.chucknorrisquotes.feature.randomjoke.Joke

/**
 * Created by rrtatasciore on 27/12/17.
 */
data class JokeAdapter(private val jokes: List<Joke>, private val query: String) : RecyclerView.Adapter<JokeAdapter.JokeViewHolder>() {

    companion object {
        private val RELATIVE_SIZE_PROPORTION = 1.3f
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): JokeViewHolder {
        val v = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.joke_row_layout, parent, false)
        return JokeViewHolder(v)
    }

    override fun onBindViewHolder(holder: JokeViewHolder?, position: Int) {
        val joke = jokes[position]
        holder!!.joke.text = joke.value
        holder.applySpans()
    }

    override fun getItemCount(): Int {
        return jokes.size
    }

    inner class JokeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val joke: TextView = itemView.findViewById(R.id.tv_joke)

        private fun getStartPositionOfQueryInJoke(joke: String, query: String): Int {
            return joke.toLowerCase().indexOf(query.toLowerCase())
        }

        fun applySpans() {
            val jokeStr = jokes[adapterPosition].value
            val startPosition = getStartPositionOfQueryInJoke(jokeStr, query)
            if (startPosition > -1) {
                val ssb = SpannableStringBuilder(jokeStr)
                val start = startPosition
                val end = start + query.length
                val styleSpan = StyleSpan(Typeface.BOLD)
                val sizeSpan = RelativeSizeSpan(RELATIVE_SIZE_PROPORTION)
                ssb.setSpan(styleSpan, start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
                ssb.setSpan(sizeSpan, start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
                joke.text = ssb
            }
        }
    }
}