package io.github.rfonzi.objectboxdemo.repositories

import io.github.rfonzi.objectboxdemo.entities.MovieEntity
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by ryan on 11/17/17.
 */

interface MovieRepository {
    fun getMovies(): Flowable<MovieEntity>
    fun getMovie(id: Long): Single<MovieEntity>
    fun getMoviesWithActor(id: Long): Flowable<MovieEntity>
}