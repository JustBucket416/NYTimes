package android.academy.nytimes.network

import android.academy.nytimes.data.NewsItem
import android.academy.nytimes.mvp.RemoteRepository
import android.academy.nytimes.utils.Constants
import android.academy.nytimes.utils.convertToNewsItem
import io.reactivex.Single
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val nyTimesApi: NYTimesApi) : RemoteRepository {


    override fun getNews(category: Constants.Category): Single<List<NewsItem>> {
        return nyTimesApi.getNews(category.networkPath).map { nyResponseRoot ->
            nyResponseRoot.results?.map { it.convertToNewsItem() }
        }
    }

    override fun getCategories(): Array<Constants.Category> {
        return Constants.Category.values()
    }


}
