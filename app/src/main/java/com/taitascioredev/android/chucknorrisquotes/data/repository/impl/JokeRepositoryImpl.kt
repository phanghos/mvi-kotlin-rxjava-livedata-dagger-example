package com.taitascioredev.android.chucknorrisquotes.data.repository.impl

import com.taitascioredev.android.chucknorrisquotes.data.entity.JokeMapper
import com.taitascioredev.android.chucknorrisquotes.data.entity.JokeQueryMapper
import com.taitascioredev.android.chucknorrisquotes.data.net.ChuckNorrisService
import com.taitascioredev.android.chucknorrisquotes.data.repository.JokeRepository
import javax.inject.Inject

/**
 * Created by rrtatasciore on 24/12/17.
 */
data class JokeRepositoryImpl @Inject constructor(
        private val service: ChuckNorrisService,
        private val jokeMapper: JokeMapper,
        private val jokeQueryMapper: JokeQueryMapper) : JokeRepository {
    override fun getRandomJoke(category: String?) = service.getRandomJoke(category).map { jokeMapper.map(it) }
    override fun queryJokes(query: String) = service.queryJokes(query).map { jokeQueryMapper.map(it) }
}