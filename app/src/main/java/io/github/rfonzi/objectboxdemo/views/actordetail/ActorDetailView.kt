package io.github.rfonzi.objectboxdemo.views.actordetail

import io.github.rfonzi.objectboxdemo.entities.ActorEntity
import io.github.rfonzi.objectboxdemo.entities.MovieEntity

/**
 * Created by ryan on 11/19/17.
 */
interface ActorDetailView {
    fun showActor(actor: ActorEntity)
    fun addMovie(movie: MovieEntity)
}