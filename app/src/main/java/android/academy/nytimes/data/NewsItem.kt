package android.academy.nytimes.data

import java.util.*

sealed class NewsItem

data class CategorizedNewsItem(val title: String,
                               val imageUrl: String?,
                               val category: String,
                               val publishDate: Date,
                               val previewText: String,
                               val url: String) : NewsItem()

data class UncategorizedNewsItem(val title: String,
                                 val imageUrl: String?,
                                 val publishDate: Date,
                                 val previewText: String,
                                 val url: String) : NewsItem()
