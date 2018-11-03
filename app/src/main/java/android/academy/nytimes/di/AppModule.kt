package android.academy.nytimes.di

import android.academy.nytimes.constants.ConstantsHolder
import android.academy.nytimes.mvp.RemoteRepository
import android.academy.nytimes.network.NYTimesApi
import android.academy.nytimes.network.RemoteRepositoryImpl
import android.academy.nytimes.network.RetrofitHelper
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideNYTimesApi(): NYTimesApi {
        return RetrofitHelper.getInstance(ConstantsHolder.BASE_URL, ConstantsHolder.TOKEN).getNyTimesApi()
    }

    @Provides
    fun provideRemoteRepository(nyTimesApi: NYTimesApi, context: Context): RemoteRepository {
        return RemoteRepositoryImpl(nyTimesApi, ConstantsHolder.getCategories(context))
    }
}