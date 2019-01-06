package com.iuriirodyk.squarerepos.di.module.viewmodel

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.annotation.MustBeDocumented
import kotlin.annotation.Target
import kotlin.annotation.Retention
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)