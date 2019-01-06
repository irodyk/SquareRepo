package com.iuriirodyk.squarerepos.di.component.sub.fragment

import com.iuriirodyk.squarerepos.ui.fragment.RepositoryDetailFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent/*(modules = ...)*/
interface RepositoryDetailFragmentSubcomponent : AndroidInjector<RepositoryDetailFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<RepositoryDetailFragment>()
}