package com.iuriirodyk.squarerepos.di.module.activity

import android.app.Activity
import com.iuriirodyk.squarerepos.di.component.sub.activity.MainActivitySubcomponent
import com.iuriirodyk.squarerepos.ui.activity.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(MainActivitySubcomponent::class))
abstract class MainActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun bindMainActivityInjectorFactory(builder: MainActivitySubcomponent.Builder): AndroidInjector.Factory<out Activity>
}