package com.iuriirodyk.squarerepos.di

import android.app.Application
import com.iuriirodyk.data.SquareRepository
import com.iuriirodyk.data.SquareRepositoryImpl
import com.iuriirodyk.data.database.SquareRepositoryDao
import com.iuriirodyk.data.network.GithubApi
import com.iuriirodyk.squarerepos.di.module.NetworkModule
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

const val SCHEDULER_MAIN_THREAD = "mainThread"
const val SCHEDULER_IO = "io"

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return application
    }

    @Provides
    fun provideSquareRepository(squareRepositoryDao: SquareRepositoryDao, githubApi: GithubApi) :
            SquareRepository = SquareRepositoryImpl(squareRepositoryDao, githubApi)

    @Provides
    @Named(SCHEDULER_MAIN_THREAD)
    fun provideAndroidMainThreadScheduler() : Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Named(SCHEDULER_IO)
    fun provideIoScheduler() : Scheduler = Schedulers.io()
}