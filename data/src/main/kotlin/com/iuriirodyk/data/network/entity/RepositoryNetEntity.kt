package com.iuriirodyk.data.network.entity

import com.google.gson.annotations.SerializedName

data class RepositoryNetEntity(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("stargazers_count") val stargazersCount: String
)