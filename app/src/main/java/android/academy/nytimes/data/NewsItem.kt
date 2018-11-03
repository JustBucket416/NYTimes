package android.academy.nytimes.data

import java.util.*

data class NewsItem(val title: String,
                    val imageUrl: String?,
                    val category: String?,
                    val publishDate: Date,
                    val previewText: String,
                    val url: String)
