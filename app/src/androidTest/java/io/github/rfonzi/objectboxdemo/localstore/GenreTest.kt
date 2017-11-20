package io.github.rfonzi.objectboxdemo.localstore

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import io.github.rfonzi.objectboxdemo.entities.GenreEntity
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
class GenreTest : ObjectBoxTest() {

    lateinit var box: Box<GenreEntity>

    override fun provideAppContext(): Context = InstrumentationRegistry.getTargetContext()

    @Before
    fun beforeEachTest() {
        box = boxStore.boxFor(GenreEntity::class.java)
    }

    @Test
    fun shouldPutAndGet() {
        val testEntity = GenreEntity(genre = "Sci-Fi")
        val id = box.put(testEntity)
        val actualEntity = box.get(id)

        assertEquals(actualEntity.genre, testEntity.genre)
    }

    @Test
    fun shouldPutAndDelete() {
        val testEntity = GenreEntity(genre = "Sci-Fi")
        val id = box.put(testEntity)
        box.remove(id)

        assertNull(box.get(id))
    }
}