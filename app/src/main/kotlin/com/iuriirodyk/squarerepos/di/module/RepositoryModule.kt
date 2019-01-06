package com.iuriirodyk.squarerepos.di.module

import android.app.Application
import androidx.room.Room
import com.iuriirodyk.data.database.SquareRepositoryDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSquareRepositoryDao(database: SquareRepositoryDatabase) = database.squareRepositoryDao()

    @Provides
    @Singleton
    fun provideSquareRepositoryDatabase(application: Application) : SquareRepositoryDatabase =
        Room.databaseBuilder(
                            application.applicationContext,
                            SquareRepositoryDatabase::class.java,
                        "square_repository_db")
            .fallbackToDestructiveMigration()
            .build()
}