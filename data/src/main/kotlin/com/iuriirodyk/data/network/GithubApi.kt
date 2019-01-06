package com.iuriirodyk.data.network

import com.iuriirodyk.data.network.entity.RepositoryNetEntity
import com.iuriirodyk.data.network.entity.StargazerNetEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("orgs/square/repos")
    fun getSquareRepositories(): Single<List<RepositoryNetEntity>>

    @GET("repos/square/{repositoryName}/stargazers")
    fun getStargazers(@Path("repositoryName") repositoryName: String): Single<List<StargazerNetEntity>>
}