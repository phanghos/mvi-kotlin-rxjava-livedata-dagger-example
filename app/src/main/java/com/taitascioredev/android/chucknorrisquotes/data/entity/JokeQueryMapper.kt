package com.taitascioredev.android.chucknorrisquotes.data.entity

import com.taitascioredev.android.chucknorrisquotes.model.JokeQuery

/**
 * Created by rrtatasciore on 27/12/17.
 */
class JokeQueryMapper {
    fun map(jokeQueryEntity: JokeQueryEntity): JokeQuery {
        return JokeQuery(jokeQueryEntity.total, jokeQueryEntity.result)
    }
}