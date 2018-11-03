package android.academy.nytimes.network.model

import com.google.gson.annotations.SerializedName

class Result {

    @SerializedName("subsection")
    val subsection: String? = null
    @SerializedName("title")
    val title: String? = null
    @SerializedName("abstract")
    val abstract: String? = null
    @SerializedName("url")
    val url: String? = null
    @SerializedName("published_date")
    val publishedDate: String? = null
    @SerializedName("multimedia")
    val multimedia: List<Multimedium>? = null
}
