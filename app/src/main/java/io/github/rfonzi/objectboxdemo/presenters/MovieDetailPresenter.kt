package io.github.rfonzi.objectboxdemo.presenters

import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.instance
import io.github.rfonzi.objectboxdemo.entities.MovieEntity
import io.github.rfonzi.objectboxdemo.repositories.MovieRepository
import io.github.rfonzi.objectboxdemo.views.moviedetail.MovieDetailView

/**
 * Created by ryan on 11/18/17.
 */
class MovieDetailPresenter(val view: MovieDetailView, kodein: LazyKodein) {

    val movieRepo: MovieRepository = kodein.invoke().instance()

    fun getMovie(id: Long) {
        movieRepo.getMovie(id)
                .subscribe({ movie -> onMovieFound(movie) },
                        {throwable -> onMovieNotFound(throwable) })
    }

    fun onMovieFound(movie: MovieEntity) {
        view.showMovie(movie)
    }

    fun onMovieNotFound(throwable: Throwable) {
        throwable.printStackTrace()
    }

}