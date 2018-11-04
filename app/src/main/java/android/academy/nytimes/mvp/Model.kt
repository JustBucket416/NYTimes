package android.academy.nytimes.mvp

import android.academy.nytimes.data.NewsItem
import android.academy.nytimes.utils.Constants
import io.reactivex.Single
import javax.inject.Inject

class Model @Inject constructor(private val remoteRepository: RemoteRepository) {

    fun loadNews(category: Constants.Category): Single<List<NewsItem>> {
        return remoteRepository.getNews(category)
    }

    fun loadCategories() = remoteRepository.getCategories()


}