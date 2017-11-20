package io.github.rfonzi.objectboxdemo.views.moviedetail

import io.github.rfonzi.objectboxdemo.entities.MovieEntity

/**
 * Created by ryan on 11/18/17.
 */
interface MovieDetailView {
    fun showMovie(movie: MovieEntity)
}