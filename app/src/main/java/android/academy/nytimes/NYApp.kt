package android.academy.nytimes

import android.academy.nytimes.di.DaggerApplicationComponent
import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class NYApp : Application(), HasActivityInjector {

    @Inject
    lateinit var androidActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidActivityInjector
    }

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent
                .builder()
                .appContext(this)
                .build()
                .inject(this)
    }
}

