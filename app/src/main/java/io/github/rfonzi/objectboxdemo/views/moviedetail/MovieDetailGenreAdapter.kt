package io.github.rfonzi.objectboxdemo.views.moviedetail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.github.rfonzi.objectboxdemo.R
import io.github.rfonzi.objectboxdemo.entities.GenreEntity

/**
 * Created by ryan on 11/18/17.
 */
class MovieDetailGenreAdapter : RecyclerView.Adapter<MovieDetailGenreAdapter.GenreViewHolder>() {
    private val list: MutableList<GenreEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GenreViewHolder{
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_genre_item, parent, false)

        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.genreText.text = list[position].genre
    }

    override fun getItemCount(): Int = list.size

    fun addGenre(genre: GenreEntity) {
        if (itemCount < 3) {
            list.add(genre)
            notifyDataSetChanged()
        }
    }

    class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val genreText: TextView = itemView.findViewById(R.id.genre_chip)
    }
}