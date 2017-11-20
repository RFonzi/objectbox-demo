package io.github.rfonzi.objectboxdemo

import android.app.Application
import android.util.Log
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.appKodein
import io.github.rfonzi.objectboxdemo.entities.ActorEntity
import io.github.rfonzi.objectboxdemo.entities.GenreEntity
import io.github.rfonzi.objectboxdemo.entities.MovieEntity
import io.github.rfonzi.objectboxdemo.entities.MyObjectBox
import io.github.rfonzi.objectboxdemo.repositories.ActorRepository
import io.github.rfonzi.objectboxdemo.repositories.MovieRepository
import io.github.rfonzi.objectboxdemo.repositories.objectbox.ObjectboxActorRepository
import io.github.rfonzi.objectboxdemo.repositories.objectbox.ObjectboxMovieRepository
import io.objectbox.Box
import io.objectbox.BoxStore

/**
 * Created by ryan on 11/17/17.
 */
class MovieApp : Application(), KodeinAware {

    lateinit var boxStore: BoxStore
        private set

    override val kodein by Kodein.lazy {
        bind<Box<MovieEntity>>() with provider { boxStore.boxFor(MovieEntity::class.java) }
        bind<Box<ActorEntity>>() with provider { boxStore.boxFor(ActorEntity::class.java) }
        bind<Box<GenreEntity>>() with provider { boxStore.boxFor(GenreEntity::class.java) }
        bind<MovieRepository>() with singleton { ObjectboxMovieRepository(boxStore) }
        bind<ActorRepository>() with singleton { ObjectboxActorRepository(boxStore) }
    }

    override fun onCreate() {
        super.onCreate()

        boxStore = MyObjectBox.builder().androidContext(applicationContext).build()
        boxStore.runInTx { removeData() }
        DummyData(LazyKodein(appKodein)).setup()
        Log.d("Done", "Done")
    }

    fun removeData() {
        val movieBox: Box<MovieEntity> = kodein.instance()
        val actorBox: Box<ActorEntity> = kodein.instance()
        val genreBox: Box<GenreEntity> = kodein.instance()

        movieBox.removeAll()
        actorBox.removeAll()
        genreBox.removeAll()
    }

}

