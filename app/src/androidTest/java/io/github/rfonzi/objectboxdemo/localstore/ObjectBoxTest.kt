package io.github.rfonzi.objectboxdemo.localstore

import android.content.Context
import io.github.rfonzi.objectboxdemo.entities.MyObjectBox
import io.objectbox.BoxStore
import org.junit.After
import org.junit.Before

/**
 * Created by ryan on 11/17/17.
 */

abstract class ObjectBoxTest {

    protected lateinit var boxStore: BoxStore

    @Before
    fun setup() {
        boxStore = MyObjectBox.builder().androidContext(provideAppContext()).build()
    }

    @After
    fun teardown() {
        boxStore.close()
        boxStore.deleteAllFiles()
    }

    abstract fun provideAppContext(): Context
}