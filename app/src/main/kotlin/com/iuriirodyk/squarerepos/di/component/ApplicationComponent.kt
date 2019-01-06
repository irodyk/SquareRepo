package com.iuriirodyk.squarerepos.di.component

import com.iuriirodyk.squarerepos.app.SquareReposApp
import com.iuriirodyk.squarerepos.di.ApplicationModule
import com.iuriirodyk.squarerepos.di.module.NetworkModule
import com.iuriirodyk.squarerepos.di.module.RepositoryModule
import com.iuriirodyk.squarerepos.di.module.viewmodel.ViewModelFactoryModule
import com.iuriirodyk.squarerepos.di.module.ViewModelModule
import com.iuriirodyk.squarerepos.di.module.activity.MainActivityModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    ApplicationModule::class,
    ViewModelFactoryModule::class,
    ViewModelModule::class,
    MainActivityModule::class,
    RepositoryModule::class,
    NetworkModule::class
))

interface ApplicationComponent {
    fun inject(squareReposApp: SquareReposApp)
}