package android.academy.nytimes.di

import android.academy.nytimes.NYApp
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,
    NetworkModule::class,
    ViewModelModule::class,
    UIModule::class,
    AndroidInjectionModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        fun build(): ApplicationComponent
    }

    fun inject(nyApp: NYApp)
}