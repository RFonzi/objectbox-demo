package io.github.rfonzi.objectboxdemo.localstore

import android.content.Context
import android.support.test.InstrumentationRegistry
import io.github.rfonzi.objectboxdemo.entities.ActorEntity
import io.github.rfonzi.objectboxdemo.entities.Female
import io.objectbox.Box
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Created by ryan on 11/17/17.
 */

class ActorTest : ObjectBoxTest() {

    lateinit var box: Box<ActorEntity>

    override fun provideAppContext(): Context = InstrumentationRegistry.getTargetContext()

    @Before
    fun beforeEachTest() {
        box = boxStore.boxFor(ActorEntity::class.java)
    }

    @Test
    fun shouldPutAndGet() {
        val testActor = ActorEntity(name = "Test Name", age = "29", gender = Female())
        val id = box.put(testActor)
        val actualActor = box.get(id)

        assertEquals(actualActor.name, testActor.name)
        assertEquals(actualActor.age, testActor.age)
        assertTrue(actualActor.gender is Female)
    }

    @Test
    fun shouldPutAndDelete() {
        val testActor = ActorEntity(name = "Test Name", age = "29", gender = Female())
        val id = box.put(testActor)
        box.remove(id)

        assertNull(box.get(id))
    }
}