package com.iuriirodyk.squarerepos.di.module.fragment

import androidx.fragment.app.Fragment
import com.iuriirodyk.squarerepos.di.component.sub.fragment.RepositoryListFragmentSubcomponent
import com.iuriirodyk.squarerepos.ui.fragment.RepositoryListFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(RepositoryListFragmentSubcomponent::class))
abstract class RepositoryListFragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(RepositoryListFragment::class)
    abstract fun bindRepositoryListFragmentInjectorFactory(builder: RepositoryListFragmentSubcomponent.Builder): AndroidInjector.Factory<out Fragment>
}