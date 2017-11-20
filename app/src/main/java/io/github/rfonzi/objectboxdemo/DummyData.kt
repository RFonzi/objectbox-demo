package io.github.rfonzi.objectboxdemo

import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.instance
import io.github.rfonzi.objectboxdemo.entities.*
import io.objectbox.Box

/**
 * Created by ryan on 11/20/17.
 */
class DummyData(kodein: LazyKodein) {

    private val movieBox: Box<MovieEntity> = kodein.invoke().instance()

    private val action = GenreEntity(genre = "action")
    private val adventure = GenreEntity(genre = "adventure")
    private val fantasy = GenreEntity(genre = "fantasy")
    private val drama = GenreEntity(genre = "drama")
    private val comedy = GenreEntity(genre = "comedy")

    private val markHamill = ActorEntity(
            name = "Mark Hamill",
            age = "66",
            gender = Male()
    )

    private val harrisonFord = ActorEntity(
            name = "Harrison Ford",
            age = "75",
            gender = Male()
    )

    private val carrieFisher = ActorEntity(
            name = "Carrie Fisher",
            age = "60",
            gender = Female()
    )

    private val tommyWiseau = ActorEntity(
            name = "Tommy Wiseau",
            age = "62",
            gender = Male()
    )

    private val gregSestero = ActorEntity(
            name = "Greg Sestero",
            age = "39",
            gender = Male()
    )

    private val julietteDanielle = ActorEntity(
            name = "Juliette Danielle",
            age = "36",
            gender = Female()
    )

    private val karenAllen = ActorEntity(
            name = "Karen Allen",
            age = "66",
            gender = Female()
    )

    private val paulFreeman = ActorEntity(
            name = "Paul Freeman",
            age = "74",
            gender = Male()
    )

    private val chrisHemsworth = ActorEntity(
            name = "Chris Hemsworth",
            age = "34",
            gender = Male()
    )

    private val tomHiddleston = ActorEntity(
            name = "Tom Hiddleston",
            age = "36",
            gender = Male()
    )

    private val cateBlanchett = ActorEntity(
            name = "Cate Blanchett",
            age = "48",
            gender = Female()
    )

    private val jeffGoldblum = ActorEntity(
            name = "Jeff Goldblum",
            age = "65",
            gender = Male()
    )

    private val markRuffalo = ActorEntity(
            name = "Mark Ruffalo",
            age = "49",
            gender = Male()
    )

    fun setup(){
        setupStarWarsEp4()
        setupStarWarsEp5()
        setupStarWarsEp6()
        setupTheRoom()
        setupRaidersOfTheLostArk()
        setupThorRagnarok()
    }
    
    private fun setupStarWarsEp4() {
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

    private fun setupStarWarsEp5() { // returns the id
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

    private fun setupStarWarsEp6() {
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

    private fun setupTheRoom() {
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

    private fun setupRaidersOfTheLostArk() {
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

    private fun setupThorRagnarok() {
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
}