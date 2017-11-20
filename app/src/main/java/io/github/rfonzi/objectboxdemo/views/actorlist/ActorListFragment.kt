package io.github.rfonzi.objectboxdemo.views.actorlist

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.appKodein
import io.github.rfonzi.objectboxdemo.R
import io.github.rfonzi.objectboxdemo.entities.ActorEntity
import io.github.rfonzi.objectboxdemo.presenters.ActorListPresenter
import io.github.rfonzi.objectboxdemo.views.actordetail.ActorDetailActivity
import io.github.rfonzi.objectboxdemo.views.moviedetail.ActorListAdapter

/**
 * Created by ryan on 11/19/17.
 */
class ActorListFragment : Fragment(), ActorListView {

    lateinit var presenter: ActorListPresenter
    lateinit var actorRecycler: RecyclerView
    val adapter = ActorListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_actor_list, container, false)

        presenter = ActorListPresenter(this, LazyKodein(appKodein))

        actorRecycler = view.findViewById(R.id.recyclerView)
        actorRecycler.adapter = adapter
        actorRecycler.layoutManager = LinearLayoutManager(context)

        presenter.getActors()

        adapter.getActorClicks()
                .subscribe { actor -> navigateToDetailView(actor.id) }

        return view
    }

    override fun showActor(actor: ActorEntity) {
        adapter.addActor(actor)
    }

    private fun navigateToDetailView(id: Long) {
        val intent = Intent(context, ActorDetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

}