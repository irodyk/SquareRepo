package com.iuriirodyk.squarerepos.viewmodel

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iuriirodyk.data.SquareRepository
import com.iuriirodyk.data.model.SquareRepositoryModel
import com.iuriirodyk.squarerepos.di.SCHEDULER_IO
import com.iuriirodyk.squarerepos.di.SCHEDULER_MAIN_THREAD
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject
import javax.inject.Named

class RepositoryListViewModel
@Inject constructor (private val squareRepository: SquareRepository,
                     @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler,
                     @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler
)
    : ViewModel(){

    @Parcelize
    data class SquareRepo(val id: String,
                          val name: String,
                          val stargazersCount: String,
                          var isBookmarked: Boolean
    ) : Parcelable

    val liveDataState =  MutableLiveData<State<SquareRepo>>()

    private val disposables = CompositeDisposable()

    init {
        liveDataState.value = DefaultState(emptyList())
        getSquareRepositories()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    fun getSquareRepositories() {
        liveDataState.value = LoadingState(obtainCurrentData())

        disposables.add(squareRepository.getSquareRepositories()
            .subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribe(this::onRepositoriesReceived, this::onError))
    }

    private fun onRepositoriesReceived(squareRepositoryModelList: List<SquareRepositoryModel>) {

        val squareRepositories = squareRepositoryModelList.map {
            SquareRepo(
                id = it.id,
                name = it.name,
                stargazersCount = it.stargazersCount,
                isBookmarked = it.isBookmarked)
        }

        liveDataState.value = DefaultState(squareRepositories)
    }

    private fun onError(error: Throwable) {
        liveDataState.value = ErrorState(error.message ?: "", obtainCurrentData())
    }

    private fun obtainCurrentData() = liveDataState.value?.data ?: emptyList()
}