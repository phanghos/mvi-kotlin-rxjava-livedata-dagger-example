package com.taitascioredev.android.chucknorrisquotes.data.entity

import com.taitascioredev.android.chucknorrisquotes.model.Joke

/**
 * Created by rrtatasciore on 24/12/17.
 */
class JokeMapper {
    fun map(jokeEntity: JokeEntity): Joke {
        with (jokeEntity) {
            return Joke(
                    category = category,
                    iconUrl = iconUrl,
                    id = id,
                    url = url,
                    value = value
            )
        }
    }
}