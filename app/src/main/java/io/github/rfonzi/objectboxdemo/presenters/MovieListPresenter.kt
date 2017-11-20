package io.github.rfonzi.objectboxdemo.presenters

import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.instance
import io.github.rfonzi.objectboxdemo.entities.MovieEntity
import io.github.rfonzi.objectboxdemo.repositories.MovieRepository
import io.github.rfonzi.objectboxdemo.views.movielist.MovieListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by ryan on 11/17/17.
 */
class MovieListPresenter(private val view: MovieListView, kodein: LazyKodein){

    val movieRepo: MovieRepository = kodein.invoke().instance()

    fun getMovies() {
        movieRepo.getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { movie -> onGetMovie(movie) }
    }

    private fun onGetMovie(movie: MovieEntity) {
        view.showMovie(movie)
    }
}