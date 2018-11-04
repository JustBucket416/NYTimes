package android.academy.nytimes.di

import android.academy.nytimes.mvp.RemoteRepository
import android.academy.nytimes.network.NYTimesApi
import android.academy.nytimes.network.RemoteRepositoryImpl
import android.academy.nytimes.utils.NewsItemConverter
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideRemoteRepository(nyTimesApi: NYTimesApi, newsItemConverter: NewsItemConverter): RemoteRepository {
        return RemoteRepositoryImpl(nyTimesApi, newsItemConverter)
    }
}