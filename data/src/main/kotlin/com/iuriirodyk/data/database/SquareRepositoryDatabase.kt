package com.iuriirodyk.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iuriirodyk.data.database.entity.BookmarkDbEntity

@Database(entities = [ BookmarkDbEntity::class ], version = 1)
abstract class SquareRepositoryDatabase : RoomDatabase() {

    abstract fun squareRepositoryDao(): SquareRepositoryDao
}