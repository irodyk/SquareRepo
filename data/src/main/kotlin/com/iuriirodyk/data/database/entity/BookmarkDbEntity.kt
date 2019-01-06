package com.iuriirodyk.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class BookmarkDbEntity(
    @PrimaryKey @ColumnInfo(name = "repository_id") val name: String
)