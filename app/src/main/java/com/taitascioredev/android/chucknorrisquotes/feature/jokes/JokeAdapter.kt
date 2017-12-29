package com.taitascioredev.android.chucknorrisquotes.feature.jokes

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.taitascioredev.android.chucknorrisquotes.R
import com.taitascioredev.android.chucknorrisquotes.model.Joke

/**
 * Created by rrtatasciore on 27/12/17.
 */
data class JokeAdapter(private val jokes: List<Joke>) : RecyclerView.Adapter<JokeAdapter.JokeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): JokeViewHolder {
        val v = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.joke_row_layout, parent, false)
        return JokeViewHolder(v)
    }

    override fun onBindViewHolder(holder: JokeViewHolder?, position: Int) {
        val joke = jokes[position]
        holder!!.joke.text = joke.value
    }

    override fun getItemCount(): Int {
        return jokes.size
    }

    class JokeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val joke: TextView = itemView.findViewById(R.id.tv_joke)
    }
}