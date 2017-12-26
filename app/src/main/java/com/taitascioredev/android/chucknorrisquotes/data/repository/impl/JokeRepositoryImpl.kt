package com.taitascioredev.android.chucknorrisquotes.data.repository.impl

import com.taitascioredev.android.chucknorrisquotes.data.entity.JokeMapper
import com.taitascioredev.android.chucknorrisquotes.data.net.ChuckNorrisService
import com.taitascioredev.android.chucknorrisquotes.data.repository.JokeRepository

/**
 * Created by rrtatasciore on 24/12/17.
 */
data class JokeRepositoryImpl(
        private val service: ChuckNorrisService,
        private val mapper: JokeMapper) : JokeRepository {
    override fun getRandomJoke(category: String?) = service.getRandomJoke(category).map { mapper.map(it) }
}