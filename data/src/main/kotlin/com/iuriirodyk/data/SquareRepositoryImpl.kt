package com.iuriirodyk.data

import com.iuriirodyk.data.database.SquareRepositoryDao
import com.iuriirodyk.data.database.entity.BookmarkDbEntity
import com.iuriirodyk.data.model.BookmarkModel
import com.iuriirodyk.data.model.SquareRepositoryModel
import com.iuriirodyk.data.model.StargazerModel
import com.iuriirodyk.data.network.GithubApi
import com.iuriirodyk.data.network.entity.RepositoryNetEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SquareRepositoryImpl
@Inject constructor(
    private val squareRepositoryDao: SquareRepositoryDao,
    private val githubApi: GithubApi
) : SquareRepository {

    override fun getSquareRepositories(): Single<List<SquareRepositoryModel>> {
        return Single.zip(
            githubApi.getSquareRepositories().subscribeOn(Schedulers.newThread()),
            squareRepositoryDao.getBookmarks().subscribeOn(Schedulers.newThread()),
            BiFunction<List<RepositoryNetEntity>, List<BookmarkDbEntity>, List<SquareRepositoryModel>>{
                    repos, bookmarks ->

                repos.map { SquareRepositoryModel(
                    id = it.id,
                    name = it.name,
                    stargazersCount = it.stargazersCount,
                    isBookmarked = bookmarks.any{ bookmark -> bookmark.name == it.name }
                )  }.toList()
            })
    }

    override fun getStargazers(name: String): Single<List<StargazerModel>> =
        githubApi.getStargazers(name)
            .flatMap{list ->
                Observable.fromIterable(list)
                    .map { stargazerNetEntity ->
                        StargazerModel(stargazerNetEntity.login, stargazerNetEntity.avatarUrl)
                    }
                    .toList() }

    override fun addBookmark(bookmarkModel: BookmarkModel): Completable =
        Completable.fromAction{
            squareRepositoryDao.insertBookmark(BookmarkDbEntity(bookmarkModel.name))
        }

    override fun removeBookmark(bookmarkModel: BookmarkModel): Completable =
        Completable.fromAction{
            squareRepositoryDao.deleteBookmark(BookmarkDbEntity(bookmarkModel.name))
        }
}