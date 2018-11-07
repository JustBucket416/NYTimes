package android.academy.nytimes.network

import android.academy.nytimes.network.model.NYResponseRoot
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface NYTimesApi {

    @GET
    fun getNews(@Url url: String): Single<NYResponseRoot>

}
