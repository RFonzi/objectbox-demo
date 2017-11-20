package io.github.rfonzi.objectboxdemo.views.movielist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.github.rfonzi.objectboxdemo.R
import io.github.rfonzi.objectboxdemo.entities.MovieEntity
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by ryan on 11/17/17.
 */
class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    private val list: MutableList<MovieEntity> = mutableListOf()
    private val itemClicks: PublishSubject<MovieEntity> = PublishSubject.create()

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.movieNameText.text = list[position].name
        holder.movieLengthText.text = "Running time: ${list[position].length} mins"

        holder.itemView.setOnClickListener { itemClicks.onNext(list[position]) }
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieListViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_movie_item, parent, false)
        return MovieListViewHolder(view)
    }

    fun addMovie(movieEntity: MovieEntity) {
        val index = list.size
        list.add(movieEntity)
        notifyDataSetChanged()
    }

    fun reset() {
        list.clear()
        notifyDataSetChanged()
    }

    fun getItemClicks(): Observable<MovieEntity> = itemClicks

    class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val movieNameText: TextView = itemView.findViewById(R.id.movie_name)
        val movieLengthText: TextView = itemView.findViewById(R.id.movie_length)
    }
}