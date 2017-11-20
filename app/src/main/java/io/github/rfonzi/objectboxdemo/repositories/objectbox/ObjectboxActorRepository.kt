package io.github.rfonzi.objectboxdemo.repositories.objectbox

import io.github.rfonzi.objectboxdemo.entities.ActorEntity
import io.github.rfonzi.objectboxdemo.entities.ActorEntity_
import io.github.rfonzi.objectboxdemo.repositories.ActorRepository
import io.objectbox.BoxStore
import io.objectbox.rx.RxQuery
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by ryan on 11/18/17.
 */
class ObjectboxActorRepository(boxStore: BoxStore) : ActorRepository {
    val actorBox = boxStore.boxFor(ActorEntity::class.java)

    val orderedByNameQuery = actorBox.query().order(ActorEntity_.name).build()

    override fun getActors(): Flowable<ActorEntity> {
        return RxQuery.flowableOneByOne(orderedByNameQuery, BackpressureStrategy.BUFFER)
    }

    override fun getActor(id: Long): Single<ActorEntity> {
        return Single.just(actorBox[id])
    }

//    private fun fetchActors(): Flowable<ActorEntity> {
//        val flowable = RxQuery.flowableOneByOne(orderedByIdQuery, BackpressureStrategy.BUFFER).cache()
//        cache.put("list", flowable)
//
//        return flowable
//    }
}