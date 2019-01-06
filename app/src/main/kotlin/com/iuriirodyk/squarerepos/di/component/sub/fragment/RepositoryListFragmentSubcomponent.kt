package com.iuriirodyk.squarerepos.di.component.sub.fragment

import com.iuriirodyk.squarerepos.ui.fragment.RepositoryListFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent/*(modules = ...)*/
interface RepositoryListFragmentSubcomponent : AndroidInjector<RepositoryListFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<RepositoryListFragment>()
}