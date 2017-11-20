package io.github.rfonzi.objectboxdemo.views.actorlist

import io.github.rfonzi.objectboxdemo.entities.ActorEntity

/**
 * Created by ryan on 11/19/17.
 */
interface ActorListView {
    fun showActor(actor: ActorEntity)
}