package android.academy.nytimes.di

import android.academy.nytimes.mvp.RemoteRepository
import android.academy.nytimes.network.NYTimesApi
import android.academy.nytimes.network.NyTimesInterceptor
import android.academy.nytimes.network.RemoteRepositoryImpl
import android.academy.nytimes.network.RetrofitHelper
import android.academy.nytimes.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor

@Module
class AppModule {

    @Provides
    fun provideInterceptor(): Interceptor {
        return NyTimesInterceptor(Constants.TOKEN)
    }

    @Provides
    fun provideNYTimesApi(interceptor: Interceptor): NYTimesApi {
        return RetrofitHelper.getInstance(Constants.BASE_URL, interceptor).getNyTimesApi()
    }

    @Provides
    fun provideRemoteRepository(nyTimesApi: NYTimesApi): RemoteRepository {
        return RemoteRepositoryImpl(nyTimesApi)
    }
}