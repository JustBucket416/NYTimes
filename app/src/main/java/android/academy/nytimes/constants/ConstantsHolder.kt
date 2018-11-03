package android.academy.nytimes.constants

import android.academy.nytimes.R
import android.content.Context

object ConstantsHolder {

    const val BASE_URL = "http://api.nytimes.com/svc/topstories/v2/"
    const val TOKEN = "aae03167237f4fbf836838d292db06a8"

    fun getCategories(context: Context) = mapOf(
            Pair(context.getString(R.string.home), "home.json"),
            Pair(context.getString(R.string.opinion), "opinion.json"),
            Pair(context.getString(R.string.world), "world.json"),
            Pair(context.getString(R.string.national), "national.json"),
            Pair(context.getString(R.string.politics), "politics.json"),
            Pair(context.getString(R.string.upshot), "upshot.json"),
            Pair(context.getString(R.string.nyregion), "nyregion.json"),
            Pair(context.getString(R.string.business), "business.json"),
            Pair(context.getString(R.string.technology), "technology.json"),
            Pair(context.getString(R.string.science), "science.json"),
            Pair(context.getString(R.string.health), "health.json"),
            Pair(context.getString(R.string.sports), "sports.json"),
            Pair(context.getString(R.string.arts), "arts.json"),
            Pair(context.getString(R.string.books), "books.json"),
            Pair(context.getString(R.string.movies), "movies.json"),
            Pair(context.getString(R.string.theater), "theater.json"),
            Pair(context.getString(R.string.sunday_review), "sundayreview.json"),
            Pair(context.getString(R.string.fashion), "fashion.json"),
            Pair(context.getString(R.string.t_magazine), "tmagazine.json"),
            Pair(context.getString(R.string.food), "food.json"),
            Pair(context.getString(R.string.travel), "travel.json"),
            Pair(context.getString(R.string.magazine), "magazine.json"),
            Pair(context.getString(R.string.real_estate), "realestate.json"),
            Pair(context.getString(R.string.automobiles), "automobiles.json"),
            Pair(context.getString(R.string.obituaries), "obituaries.json"),
            Pair(context.getString(R.string.insider), "insider.json")
    )
}