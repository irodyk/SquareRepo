package com.iuriirodyk.data.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.filters.MediumTest
import androidx.test.runner.AndroidJUnit4
import com.iuriirodyk.data.database.entity.BookmarkDbEntity
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class SquareRepositoryDatabaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private var squareRepositoryDatabase: SquareRepositoryDatabase? = null
    private var squareRepositoryDao: SquareRepositoryDao? = null

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        squareRepositoryDatabase = Room.inMemoryDatabaseBuilder(context, SquareRepositoryDatabase::class.java)
            .allowMainThreadQueries().build()
        squareRepositoryDao = squareRepositoryDatabase!!.squareRepositoryDao()
    }

    @After
    fun tearDown() {
        squareRepositoryDatabase!!.close()
    }

    @Test
    fun testDbEmpty() {
        assertEquals(0, squareRepositoryDao!!.getBookmarks().blockingGet().size)
    }

    @Test
    fun testInsertBookmark() {
        val testEntity = BookmarkDbEntity("repository 1")

        val id = squareRepositoryDao!!.insertBookmark(testEntity)
        assertTrue(id >= 0)

        val resultEntity = squareRepositoryDao!!.getBookmarks().blockingGet()[0]
        assertEquals(resultEntity.name, "repository 1")
    }

    @Test
    fun testRemoveBookmark() {
        val testEntity = BookmarkDbEntity("repository 1")

        val id = squareRepositoryDao!!.insertBookmark(testEntity)
        assertTrue(id >= 0)

        val result = squareRepositoryDao!!.deleteBookmark(testEntity)
        assertEquals(1, result)

        assertEquals(0, squareRepositoryDao!!.getBookmarks().blockingGet().size)
    }
}