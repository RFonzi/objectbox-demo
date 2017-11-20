package io.github.rfonzi.objectboxdemo.presenters

import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.instance
import io.github.rfonzi.objectboxdemo.entities.ActorEntity
import io.github.rfonzi.objectboxdemo.repositories.ActorRepository
import io.github.rfonzi.objectboxdemo.views.actorlist.ActorListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by ryan on 11/19/17.
 */
class ActorListPresenter(private val view: ActorListView, kodein: LazyKodein) {

    private val actorRepo: ActorRepository = kodein.invoke().instance()

    fun getActors() {
        actorRepo.getActors()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { actor -> onGetActor(actor) }
    }

    private fun onGetActor(actor: ActorEntity) {
        view.showActor(actor)
    }

}