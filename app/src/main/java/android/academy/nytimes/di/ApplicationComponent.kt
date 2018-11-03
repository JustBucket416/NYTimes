package android.academy.nytimes.di

import android.academy.nytimes.NYApp
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,
    ViewModelModule::class,
    UIModule::class,
    AndroidInjectionModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): ApplicationComponent
    }

    fun inject(nyApp: NYApp)
}