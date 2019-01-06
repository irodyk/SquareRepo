package com.iuriirodyk.squarerepos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iuriirodyk.data.SquareRepository
import com.iuriirodyk.data.model.BookmarkModel
import com.iuriirodyk.data.model.StargazerModel
import com.iuriirodyk.squarerepos.di.SCHEDULER_IO
import com.iuriirodyk.squarerepos.di.SCHEDULER_MAIN_THREAD
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class RepositoryDetailViewModel
@Inject constructor (private val squareRepository: SquareRepository,
                     @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler,
                     @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler
)
    : ViewModel(){

    data class Stargazer(
        val login: String,
        val avatarUrl: String
    )

    val liveDataState =  MutableLiveData<State<Stargazer>>()

    private val disposables = CompositeDisposable()

    init {
        liveDataState.value = DefaultState(emptyList())
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    fun addBookmark(repoName: String) {disposables.add(
        squareRepository.addBookmark(BookmarkModel(repoName))
            .subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribe({ }, this::onError)
    )
    }

    fun removeBookmark(repoName: String) {
        disposables.add(
            squareRepository.removeBookmark(BookmarkModel(repoName))
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribe({ }, this::onError)
        )
    }

    fun getRepositoryDetails(repoName: String) {
        liveDataState.value = LoadingState(obtainCurrentData())

        disposables.add(squareRepository.getStargazers(repoName)
            .subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribe(this::onDetailReceived, this::onError))
    }

    private fun onDetailReceived(stargazerModelList: List<StargazerModel>) {

        val stargazers = stargazerModelList.map {
            Stargazer( login =  it.login, avatarUrl = it.avatarUrl )
        }

        liveDataState.value = DefaultState(stargazers)
    }

    private fun onError(error: Throwable) {
        liveDataState.value = ErrorState(error.message ?: "", obtainCurrentData())
    }

    private fun obtainCurrentData() = liveDataState.value?.data ?: emptyList()
}