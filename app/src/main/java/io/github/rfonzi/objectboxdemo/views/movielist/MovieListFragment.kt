package io.github.rfonzi.objectboxdemo.views.movielist

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
import io.github.rfonzi.objectboxdemo.entities.MovieEntity
import io.github.rfonzi.objectboxdemo.presenters.MovieListPresenter
import io.github.rfonzi.objectboxdemo.views.moviedetail.MovieDetailActivity

class MovieListFragment : Fragment(), MovieListView {

    lateinit var presenter: MovieListPresenter
    lateinit var recyclerView: RecyclerView
    val adapter = MovieListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        presenter = MovieListPresenter(this, LazyKodein(appKodein))

        presenter.getMovies()

        adapter.getItemClicks()
                .subscribe { movie -> navigateToDetailView(movie.id) }

        return view
    }

    override fun showMovie(movie: MovieEntity) {
        adapter.addMovie(movie)
    }

    private fun navigateToDetailView(id: Long) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}
