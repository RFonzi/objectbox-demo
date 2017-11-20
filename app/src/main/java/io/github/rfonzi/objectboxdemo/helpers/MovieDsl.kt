package io.github.rfonzi.objectboxdemo.helpers

import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.instance
import io.github.rfonzi.objectboxdemo.entities.ActorEntity
import io.github.rfonzi.objectboxdemo.entities.GenreEntity
import io.github.rfonzi.objectboxdemo.entities.MovieEntity
import io.objectbox.Box

/**
 * Created by ryan on 11/17/17.
 */
class MovieDsl(kodein: LazyKodein) {

    val movieBox: Box<MovieEntity> = kodein.invoke().instance()
    val actorBox: Box<ActorEntity> = kodein.invoke().instance()
    val genreBox: Box<GenreEntity> = kodein.invoke().instance()

    fun actors() {

    }
}

fun build(kodein: LazyKodein, init: MovieDsl.() -> Unit): MovieDsl {
    val dsl = MovieDsl(kodein)
    dsl.init()
    return dsl
}