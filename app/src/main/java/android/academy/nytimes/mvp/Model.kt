package android.academy.nytimes.mvp

import android.academy.nytimes.data.NewsItem
import io.reactivex.Single
import javax.inject.Inject

class Model @Inject constructor(private val remoteRepository: RemoteRepository) {

    fun loadNews(category: String): Single<List<NewsItem>> {
        return remoteRepository.getNews(category)
    }

    fun loadCategories() = remoteRepository.getCategories()


}