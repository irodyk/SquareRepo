package com.iuriirodyk.data.network.entity

import com.google.gson.annotations.SerializedName

data class StargazerNetEntity(
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)