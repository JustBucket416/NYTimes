package android.academy.nytimes.network.model

import com.google.gson.annotations.SerializedName

data class Multimedium(@SerializedName("url")
                       val url: String? = null)
