package io.github.rfonzi.objectboxdemo.views.moviedetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.appKodein
import com.google.android.flexbox.FlexboxLayoutManager
import io.github.rfonzi.objectboxdemo.R
import io.github.rfonzi.objectboxdemo.entities.MovieEntity
import io.github.rfonzi.objectboxdemo.presenters.MovieDetailPresenter

class MovieDetailActivity : AppCompatActivity(), MovieDetailView {

    lateinit var presenter: MovieDetailPresenter

    lateinit var movieNameText: TextView
    lateinit var movieLengthText: TextView
    lateinit var movieGenreRecycler: RecyclerView
    lateinit var movieActorRecycler: RecyclerView

    val genreAdapter = MovieDetailGenreAdapter()
    val actorAdapter = ActorListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val id = intent.getLongExtra("id", 0)

        presenter = MovieDetailPresenter(this, LazyKodein(appKodein))

        movieNameText = findViewById(R.id.movie_name)
        movieLengthText = findViewById(R.id.movie_length)
        movieGenreRecycler = findViewById(R.id.movie_genre_list)
        movieActorRecycler = findViewById(R.id.movie_actors_list)

        movieGenreRecycler.adapter = genreAdapter
        movieGenreRecycler.layoutManager = FlexboxLayoutManager(this)
        movieActorRecycler.adapter = actorAdapter
        movieActorRecycler.layoutManager = LinearLayoutManager(this)

        presenter.getMovie(id)

    }

    override fun showMovie(movie: MovieEntity) {
        movieNameText.text = movie.name
        movieLengthText.text = "${movie.length} mins"

        movie.genres?.forEach { genreAdapter.addGenre(it) }
        movie.actors?.forEach { actorAdapter.addActor(it) }
    }
}
