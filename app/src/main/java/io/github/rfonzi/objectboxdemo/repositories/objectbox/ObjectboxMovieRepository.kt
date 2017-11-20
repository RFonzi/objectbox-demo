package io.github.rfonzi.objectboxdemo.repositories.objectbox

import android.support.v4.util.LruCache
import io.github.rfonzi.objectboxdemo.entities.ActorEntity
import io.github.rfonzi.objectboxdemo.entities.MovieEntity
import io.github.rfonzi.objectboxdemo.entities.MovieEntity_
import io.github.rfonzi.objectboxdemo.repositories.MovieRepository
import io.objectbox.BoxStore
import io.objectbox.rx.RxQuery
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by ryan on 11/17/17.
 */
class ObjectboxMovieRepository(boxStore: BoxStore) : MovieRepository {

    private val movieBox = boxStore.boxFor(MovieEntity::class.java)
    private val actorBox = boxStore.boxFor(ActorEntity::class.java)

    private val cache: LruCache<String, Flowable<MovieEntity>> = LruCache(10)

    private val orderedByNameQuery = movieBox.query().order(MovieEntity_.name).build()

    override fun getMovies(): Flowable<MovieEntity> {
        return cache.get("list") ?: fetchMovies()
    }

    override fun getMovie(id: Long): Single<MovieEntity> {
        return Single.just(movieBox[id])
    }

    override fun getMoviesWithActor(actorId: Long): Flowable<MovieEntity> {
        val actor = actorBox[actorId]

        val moviesWithActorQuery = movieBox.query().filter {
            it.actors.contains(actor)
        }.build()

        return RxQuery.flowableOneByOne(moviesWithActorQuery, BackpressureStrategy.BUFFER)
    }

    private fun fetchMovies(): Flowable<MovieEntity> {
        val flowable = RxQuery.flowableOneByOne(orderedByNameQuery, BackpressureStrategy.BUFFER).cache()
        cache.put("list", flowable)

        return flowable
    }

}