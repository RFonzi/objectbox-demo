package io.github.rfonzi.objectboxdemo

import android.app.Application
import android.util.Log
import com.github.salomonbrys.kodein.*
import io.github.rfonzi.objectboxdemo.entities.*
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
        setupData()
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

    fun setupData() {
        val movieBox: Box<MovieEntity> = kodein.instance()

        val action = GenreEntity(genre = "action")
        val adventure = GenreEntity(genre = "adventure")
        val fantasy = GenreEntity(genre = "fantasy")
        val drama = GenreEntity(genre = "drama")
        val comedy = GenreEntity(genre = "comedy")

        val markHamill = ActorEntity(
                name = "Mark Hamill",
                age = "66",
                gender = Male()
        )

        val harrisonFord = ActorEntity(
                name = "Harrison Ford",
                age = "75",
                gender = Male()
        )

        val carrieFisher = ActorEntity(
                name = "Carrie Fisher",
                age = "60 (rip)",
                gender = Female()
        )

        val tommyWiseau = ActorEntity(
                name = "Tommy Wiseau",
                age = "62",
                gender = Male()
        )

        val gregSestero = ActorEntity(
                name = "Greg Sestero",
                age = "39",
                gender = Male()
        )

        val julietteDanielle = ActorEntity(
                name = "Juliette Danielle",
                age = "36",
                gender = Female()
        )

        val karenAllen = ActorEntity(
                name = "Karen Allen",
                age = "66",
                gender = Female()
        )

        val paulFreeman = ActorEntity(
                name = "Paul Freeman",
                age = "74",
                gender = Male()
        )

        val chrisHemsworth = ActorEntity(
                name = "Chris Hemsworth",
                age = "34",
                gender = Male()
        )

        val tomHiddleston = ActorEntity(
                name = "Tom Hiddleston",
                age = "36",
                gender = Male()
        )

        val cateBlanchett = ActorEntity(
                name = "Cate Blanchett",
                age = "48",
                gender = Female()
        )

        val jeffGoldblum = ActorEntity(
                name = "Jeff Goldblum",
                age = "65",
                gender = Male()
        )

        val markRuffalo = ActorEntity(
                name = "Mark Ruffalo",
                age = "49",
                gender = Male()
        )

        fun setupStarWarsEp4() {
            val ep4 = MovieEntity(
                    name = "Star Wars: Episode IV - A New Hope",
                    length = 121
            )
            val ep4Actors = listOf(markHamill, harrisonFord, carrieFisher)
            val ep4Genres = listOf(action, adventure, fantasy)
            ep4.actors.addAll(ep4Actors)
            ep4.genres.addAll(ep4Genres)

            movieBox.put(ep4)
        }

        fun setupStarWarsEp5() { // returns the id
            val ep5 = MovieEntity(
                    name = "Star Wars: Episode V - The Empire Strikes Back",
                    length = 124
            )
            val ep5Actors = listOf(markHamill, harrisonFord, carrieFisher)
            val ep5Genres = listOf(action, adventure, fantasy)
            ep5.actors.addAll(ep5Actors)
            ep5.genres.addAll(ep5Genres)

            movieBox.put(ep5)
        }

        fun setupStarWarsEp6() {
            val ep6 = MovieEntity(
                    name = "Star Wars: Episode VI - Return of the Jedi",
                    length = 131
            )
            val ep6Actors = listOf(markHamill, harrisonFord, carrieFisher)
            val ep6Genres = listOf(action, adventure, fantasy)
            ep6.actors.addAll(ep6Actors)
            ep6.genres.addAll(ep6Genres)

            movieBox.put(ep6)
        }

        fun setupTheRoom() {
            val room = MovieEntity(
                    name = "The Room",
                    length = 99
            )
            val roomActors = listOf(tommyWiseau, gregSestero, julietteDanielle)
            val roomGenres = listOf(drama)
            room.actors.addAll(roomActors)
            room.genres.addAll(roomGenres)

            movieBox.put(room)
        }

        fun setupRaidersOfTheLostArk() {
            val raiders = MovieEntity(
                    name = "Raiders of the Lost Ark",
                    length = 115
            )
            val raidersActors = listOf(harrisonFord, karenAllen, paulFreeman)
            val raidersGenres = listOf(action, adventure)
            raiders.actors.addAll(raidersActors)
            raiders.genres.addAll(raidersGenres)

            movieBox.put(raiders)
        }

        fun setupThorRagnarok() {
            val ragnarok = MovieEntity(
                    name = "Thor: Ragnarok",
                    length = 130
            )
            val ragnarokActors = listOf(chrisHemsworth, tomHiddleston, cateBlanchett, jeffGoldblum, markRuffalo)
            val ragnarokGenres = listOf(action, adventure, comedy)
            ragnarok.actors.addAll(ragnarokActors)
            ragnarok.genres.addAll(ragnarokGenres)

            movieBox.put(ragnarok)
        }

        setupStarWarsEp4()
        setupStarWarsEp5()
        setupStarWarsEp6()
        setupTheRoom()
        setupRaidersOfTheLostArk()
        setupThorRagnarok()
    }

}

