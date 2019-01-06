package com.iuriirodyk.squarerepos.di.module.viewmodel

import androidx.lifecycle.ViewModel
import com.iuriirodyk.squarerepos.viewmodel.RepositoryDetailViewModel
import com.iuriirodyk.squarerepos.viewmodel.RepositoryListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RepositoryListViewModel::class)
    abstract fun bindRepositoryListViewModel(viewModel: RepositoryListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepositoryDetailViewModel::class)
    abstract fun bindRepositoryDetailViewModel(viewModel: RepositoryDetailViewModel): ViewModel
}