package com.iuriirodyk.data

import com.iuriirodyk.data.model.BookmarkModel
import com.iuriirodyk.data.model.SquareRepositoryModel
import com.iuriirodyk.data.model.StargazerModel
import io.reactivex.Completable
import io.reactivex.Single

interface SquareRepository {

    fun getSquareRepositories() : Single<List<SquareRepositoryModel>>

    fun getStargazers(name : String) : Single<List<StargazerModel>>

    fun addBookmark(bookmarkModel: BookmarkModel) : Completable

    fun removeBookmark(bookmarkModel: BookmarkModel) : Completable
}