package io.github.rfonzi.objectboxdemo.localstore

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import io.github.rfonzi.objectboxdemo.entities.*
import io.objectbox.Box
import junit.framework.Assert
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by ryan on 11/17/17.
 */

@RunWith(AndroidJUnit4::class)
class MovieTest : ObjectBoxTest() {

    override fun provideAppContext(): Context = InstrumentationRegistry.getTargetContext()

    lateinit var movieBox: Box<MovieEntity>
    lateinit var actorBox: Box<ActorEntity>
    lateinit var genreBox: Box<GenreEntity>

    val theRoom = MovieEntity(name = "The Room", length = 99)

    val drama = GenreEntity(genre = "Drama")

    val tommyWiseau = ActorEntity(
            name = "Tommy Wiseau",
            age = "62",
            gender = Male())
    val gregSestero = ActorEntity(
            name = "Greg Sestero",
            age = "39",
            gender = Male())
    val julietteDanielle = ActorEntity(
            name = "Juliette Danielle",
            age = "36",
            gender = Female())

    lateinit var testMovie: MovieEntity
    lateinit var testActors: List<ActorEntity>
    lateinit var testGenre: GenreEntity

    @Before
    fun beforeEachTest() {
        movieBox = boxStore.boxFor(MovieEntity::class.java)
        actorBox = boxStore.boxFor(ActorEntity::class.java)
        genreBox = boxStore.boxFor(GenreEntity::class.java)

        testMovie = theRoom.copy()
        testActors = listOf(tommyWiseau.copy(), gregSestero.copy(), julietteDanielle.copy())
        testGenre = drama.copy()

        testMovie.actors.addAll(testActors)
        testMovie.genres.add(testGenre)
    }

    @Test
    fun shouldPutAndGet() {
        val id = movieBox.put(testMovie)
        val actualMovie = movieBox[id]

        // the id for testMovie got updated when calling Box::put, which is why we can do a direct
        // comparison here
        assertEquals(testMovie, actualMovie)
    }

    @Test
    fun shouldPutAndDelete() {
        val id = movieBox.put(testMovie)
        movieBox.remove(id)

        assertNull(movieBox[id])
        assertEquals(actorBox[testActors.map { it.id }].size, testMovie.actors.toList().size)
        assertTrue(actorBox[testActors.map { it.id }].containsAll(testMovie.actors.toList()))
    }
}