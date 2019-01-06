package com.iuriirodyk.squarerepos.viewmodel

abstract class State<out T> {

    abstract val data: List<T>
}
data class DefaultState<out T>(override val data: List<T>) : State<T>()
data class LoadingState<out T>(override val data: List<T>) : State<T>()
data class ErrorState<out T>(val errorMessage: String, override val data: List<T>) : State<T>()