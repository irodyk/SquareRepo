package com.iuriirodyk.squarerepos.di.component.sub.activity

import com.iuriirodyk.squarerepos.di.module.fragment.RepositoryDetailFragmentModule
import com.iuriirodyk.squarerepos.di.module.fragment.RepositoryListFragmentModule
import com.iuriirodyk.squarerepos.ui.activity.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(
    RepositoryListFragmentModule::class,
    RepositoryDetailFragmentModule::class
))
interface MainActivitySubcomponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}