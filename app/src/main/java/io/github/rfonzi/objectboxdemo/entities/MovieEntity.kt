package io.github.rfonzi.objectboxdemo.entities

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

/**
 * Created by ryan on 11/17/17.
 */

@Entity
data class MovieEntity(
        @Id var id: Long = 0,
        var name: String? = null,
        var length: Int? = null
) {
    lateinit var actors: ToMany<ActorEntity>
    lateinit var genres: ToMany<GenreEntity>

    override fun equals(other: Any?): Boolean {
        val otherMovie = other as MovieEntity

        return actors.toList() == otherMovie.actors.toList()
                && genres.toList() == otherMovie.genres.toList()
                && name == otherMovie.name
                && length == otherMovie.length
                && id == otherMovie.id
    }
}