package io.github.rfonzi.objectboxdemo.views

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import io.github.rfonzi.objectboxdemo.R
import io.github.rfonzi.objectboxdemo.views.actorlist.ActorListFragment
import io.github.rfonzi.objectboxdemo.views.movielist.MovieListFragment

class MainActivity : AppCompatActivity() {

    lateinit var bottomNav: BottomNavigationView
    lateinit var fragmentContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.main_bottomnav)
        fragmentContainer = findViewById(R.id.fragment_container)

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_movies -> switchToMoviesFragment()
                R.id.action_actors -> switchToActorsFragment()
                else -> false
            }

            true
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(fragmentContainer.id, MovieListFragment())
                    .commit()
        }

    }

    fun switchToMoviesFragment() {
        supportFragmentManager.beginTransaction()
                .replace(fragmentContainer.id, MovieListFragment())
                .commit()
    }

    fun switchToActorsFragment() {
        supportFragmentManager.beginTransaction()
                .replace(fragmentContainer.id, ActorListFragment())
                .commit()
    }
}
