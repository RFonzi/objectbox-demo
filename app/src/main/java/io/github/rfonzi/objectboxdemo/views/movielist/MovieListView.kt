package io.github.rfonzi.objectboxdemo.views.movielist

import io.github.rfonzi.objectboxdemo.entities.MovieEntity

/**
 * Created by ryan on 11/17/17.
 */
interface MovieListView {
    fun showMovie(movie: MovieEntity)
}