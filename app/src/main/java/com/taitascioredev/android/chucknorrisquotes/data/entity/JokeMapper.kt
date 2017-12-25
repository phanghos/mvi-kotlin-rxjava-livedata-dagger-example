package com.taitascioredev.android.chucknorrisquotes.data.entity

import com.taitascioredev.android.chucknorrisquotes.model.Joke

/**
 * Created by rrtatasciore on 24/12/17.
 */
class JokeMapper {
    fun map(jokeEntity: JokeEntity): Joke {
        val joke = Joke()
        with (jokeEntity) {
            joke.category = category
            joke.iconUrl = iconUrl
            joke.id = id
            joke.url = url
            joke.value = value
        }
        return joke
    }
}