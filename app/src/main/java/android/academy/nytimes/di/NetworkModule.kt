package android.academy.nytimes.di

import android.academy.nytimes.network.NYTimesApi
import android.academy.nytimes.network.NyTimesInterceptor
import android.academy.nytimes.network.RetrofitHelper
import android.academy.nytimes.utils.Constants
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideNYTimesApi(nyTimesInterceptor: NyTimesInterceptor): NYTimesApi {
        return RetrofitHelper.getInstance(Constants.BASE_URL, nyTimesInterceptor).getNyTimesApi()
    }
}