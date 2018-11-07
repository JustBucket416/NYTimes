package android.academy.nytimes

import android.academy.nytimes.di.DaggerApplicationComponent
import android.app.Activity
import android.app.Application
import android.util.Log
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import java.io.IOException
import java.net.SocketException
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

        RxJavaPlugins.setErrorHandler {
            val throwable = if (it is UndeliverableException) it.cause else it
            when {
                (throwable is IOException || throwable is SocketException) -> {
                    return@setErrorHandler
                }
                throwable is InterruptedException -> return@setErrorHandler
                (throwable is NullPointerException || throwable is IllegalArgumentException) -> {
                    Thread.currentThread().uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), throwable)
                    return@setErrorHandler
                }
                throwable is IllegalStateException -> {
                    Thread.currentThread().uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), throwable)
                    return@setErrorHandler
                }
                else -> Log.w(TAG, throwable)
            }
        }
    }
}

