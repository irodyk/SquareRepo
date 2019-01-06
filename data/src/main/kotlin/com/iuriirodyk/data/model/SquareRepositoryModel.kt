package com.iuriirodyk.data.model

data class SquareRepositoryModel(
    val id: String,
    val name: String,
    val stargazersCount: String,
    val isBookmarked: Boolean = false
)