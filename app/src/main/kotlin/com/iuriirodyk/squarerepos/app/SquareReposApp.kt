package com.iuriirodyk.squarerepos.app

import android.app.Activity
import android.app.Application
import com.iuriirodyk.squarerepos.di.ApplicationModule
import com.iuriirodyk.squarerepos.di.component.DaggerApplicationComponent
import com.iuriirodyk.squarerepos.di.module.NetworkModule
import com.iuriirodyk.squarerepos.di.module.RepositoryModule
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class SquareReposApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>
    lateinit var leakRefWatcher: RefWatcher

    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this))
            return

        leakRefWatcher = LeakCanary.install(this)

        initializeInjector()
    }

    private fun initializeInjector() {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .repositoryModule(RepositoryModule())
            .networkModule(NetworkModule())
            .build()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector
}