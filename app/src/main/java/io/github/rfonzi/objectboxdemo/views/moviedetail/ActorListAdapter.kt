package io.github.rfonzi.objectboxdemo.views.moviedetail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.github.rfonzi.objectboxdemo.R
import io.github.rfonzi.objectboxdemo.entities.*
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.*

/**
 * Created by ryan on 11/18/17.
 */
class ActorListAdapter : RecyclerView.Adapter<ActorListAdapter.ActorViewHolder>() {

    private val list: MutableList<ActorEntity> = mutableListOf()
    private val actorClicks: PublishSubject<ActorEntity> = PublishSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ActorViewHolder{
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_actor_item, parent, false)

        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.actorNameText.text = list[position].name
        holder.actorAgeText.text = list[position].age

        when (list[position].gender) {
            is Male -> holder.actorGenderText.text = "M"
            is Female -> holder.actorGenderText.text = "F"
            is Unknown -> holder.actorGenderText.text = "?"
        }

        holder.itemView.setOnClickListener {
            actorClicks.onNext(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    fun addActor(actor: ActorEntity) {
        list.add(actor)
        notifyDataSetChanged()
    }

    fun getActorClicks(): Observable<ActorEntity> = actorClicks

    class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val actorNameText: TextView = itemView.findViewById(R.id.actor_name)
        val actorAgeText: TextView = itemView.findViewById(R.id.actor_age)
        val actorGenderText: TextView = itemView.findViewById(R.id.actor_gender)
    }
}