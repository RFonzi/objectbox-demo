package io.github.rfonzi.objectboxdemo.presenters

import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.instance
import io.github.rfonzi.objectboxdemo.entities.ActorEntity
import io.github.rfonzi.objectboxdemo.entities.MovieEntity
import io.github.rfonzi.objectboxdemo.repositories.ActorRepository
import io.github.rfonzi.objectboxdemo.repositories.MovieRepository
import io.github.rfonzi.objectboxdemo.views.actordetail.ActorDetailView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by ryan on 11/19/17.
 */
class ActorDetailPresenter(private val view: ActorDetailView, kodein: LazyKodein) {

    private val actorRepo: ActorRepository = kodein.invoke().instance()
    private val movieRepo: MovieRepository = kodein.invoke().instance()

    fun getActor(id: Long) {
        actorRepo.getActor(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { actor -> onGetActor(actor) }
    }

    fun getMoviesWithActor(actorId: Long) {
        movieRepo.getMoviesWithActor(actorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { movie -> onGetMovie(movie) }
    }

    private fun onGetActor(actor: ActorEntity) {
        view.showActor(actor)
    }

    private fun onGetMovie(movie: MovieEntity) {
        view.addMovie(movie)
    }
}