package android.academy.nytimes.network.model

import com.google.gson.annotations.SerializedName

data class NYResponseRoot(@SerializedName("results")
                          val results: List<Result>? = null)
