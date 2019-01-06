package com.iuriirodyk.squarerepos.di.module.fragment

import androidx.fragment.app.Fragment
import com.iuriirodyk.squarerepos.di.component.sub.fragment.RepositoryDetailFragmentSubcomponent
import com.iuriirodyk.squarerepos.ui.fragment.RepositoryDetailFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(RepositoryDetailFragmentSubcomponent::class))
abstract class RepositoryDetailFragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(RepositoryDetailFragment::class)
    abstract fun bindRepositoryDetailFragmentInjectorFactory(builder: RepositoryDetailFragmentSubcomponent.Builder): AndroidInjector.Factory<out Fragment>
}