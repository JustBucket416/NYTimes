package android.academy.nytimes

import android.academy.nytimes.di.DaggerApplicationComponent
import android.app.Activity
import android.app.Application
import android.util.Log
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.reactivex.plugins.RxJavaPlugins
import javax.inject.Inject

class NYApp : Application(), HasActivityInjector {

    companion object {
        private const val TAG = "NYTimes"
    }

    @Inject
    lateinit var androidActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidActivityInjector
    }

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent
                .builder()
                .build()
                .inject(this)

        RxJavaPlugins.setErrorHandler { Log.wtf(TAG, it.message) }
    }
}

