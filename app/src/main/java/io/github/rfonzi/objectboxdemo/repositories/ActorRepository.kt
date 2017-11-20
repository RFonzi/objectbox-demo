package io.github.rfonzi.objectboxdemo.repositories

import io.github.rfonzi.objectboxdemo.entities.ActorEntity
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by ryan on 11/18/17.
 */
interface ActorRepository {

    fun getActors(): Flowable<ActorEntity>
    fun getActor(id: Long): Single<ActorEntity>
}