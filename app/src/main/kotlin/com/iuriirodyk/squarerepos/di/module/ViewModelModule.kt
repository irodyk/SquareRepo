package com.iuriirodyk.squarerepos.di.module

import androidx.lifecycle.ViewModel
import com.iuriirodyk.squarerepos.viewmodel.RepositoryDetailViewModel
import com.iuriirodyk.squarerepos.viewmodel.RepositoryListViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RepositoryListViewModel::class)
    abstract fun bindRepositoryListViewModel(repositoryListViewModel: RepositoryListViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepositoryDetailViewModel::class)
    abstract fun bindRepositoryDetailViewModel(repositoryDetailViewModel: RepositoryDetailViewModel) : ViewModel
}