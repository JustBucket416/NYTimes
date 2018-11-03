package android.academy.nytimes.network;

import android.academy.nytimes.data.NewsItem;
import android.academy.nytimes.network.model.NYResponseRoot;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

interface NYTimesApi {

    @GET
    Call<NYResponseRoot> getNews(@Url String url, @Query("api-key") String apiKey);

}
