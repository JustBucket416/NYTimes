package android.academy.nytimes.di

import android.academy.nytimes.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UIModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}