package com.iuriirodyk.data.database

import androidx.room.*
import com.iuriirodyk.data.database.entity.BookmarkDbEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface SquareRepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookmark(bookmarkDbEntity: BookmarkDbEntity) : Long

    @Delete
    fun deleteBookmark(bookmarkDbEntity: BookmarkDbEntity) : Int

    @Query("SELECT * from bookmark")
    fun getBookmarks() : Single<List<BookmarkDbEntity>>
}