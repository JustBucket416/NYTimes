package android.academy.nytimes.network

import android.academy.nytimes.data.NewsItem
import android.academy.nytimes.mvp.RemoteRepository
import android.academy.nytimes.utils.Category
import android.academy.nytimes.utils.NewsItemConverter
import io.reactivex.Single
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val nyTimesApi: NYTimesApi,
                                               private val newsItemConverter: NewsItemConverter) : RemoteRepository {


    override fun getNews(category: Category): Single<List<NewsItem>> {
        return nyTimesApi.getNews(category.networkPath).map { nyResponseRoot ->
            nyResponseRoot.results?.map { newsItemConverter.convertToNewsItem(it) }
        }
    }

    override fun getCategories(): Array<Category> {
        return Category.values()
    }


}
