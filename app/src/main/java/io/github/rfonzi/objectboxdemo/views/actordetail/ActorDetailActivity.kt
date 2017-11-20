package io.github.rfonzi.objectboxdemo.views.actordetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.appKodein
import io.github.rfonzi.objectboxdemo.R
import io.github.rfonzi.objectboxdemo.entities.ActorEntity
import io.github.rfonzi.objectboxdemo.entities.MovieEntity
import io.github.rfonzi.objectboxdemo.presenters.ActorDetailPresenter
import io.github.rfonzi.objectboxdemo.views.movielist.MovieListAdapter

class ActorDetailActivity : AppCompatActivity(), ActorDetailView {

    lateinit var presenter: ActorDetailPresenter

    lateinit var actorAgeText: TextView
    lateinit var actorGenderText: TextView
    lateinit var actorMoviesRecycler: RecyclerView

    val moviesAdapter = MovieListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actor_detail)

        val id = intent.getLongExtra("id", 0)

        presenter = ActorDetailPresenter(this, LazyKodein(appKodein))

        actorAgeText = findViewById(R.id.actor_age)
        actorGenderText = findViewById(R.id.actor_gender)
        actorMoviesRecycler = findViewById(R.id.recyclerView)

        actorMoviesRecycler.adapter = moviesAdapter
        actorMoviesRecycler.layoutManager = LinearLayoutManager(this)

        presenter.getActor(id)
        presenter.getMoviesWithActor(id)
    }

    override fun showActor(actor: ActorEntity) {
        supportActionBar?.title = actor.name

        actorAgeText.text = actor.age
        actorGenderText.text = actor.gender.value
    }

    override fun addMovie(movie: MovieEntity) {
        moviesAdapter.addMovie(movie)
    }

}
