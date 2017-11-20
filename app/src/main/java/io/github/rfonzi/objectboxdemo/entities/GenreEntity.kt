package io.github.rfonzi.objectboxdemo.entities

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by ryan on 11/17/17.
 */

@Entity
data class GenreEntity(
        @Id var id: Long = 0,
        var genre: String? = null
){
    override fun equals(other: Any?): Boolean {
        val otherGenre = other as GenreEntity

        return genre == otherGenre.genre
    }
}