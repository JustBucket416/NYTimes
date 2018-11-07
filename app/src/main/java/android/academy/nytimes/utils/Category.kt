package android.academy.nytimes.utils

import android.academy.nytimes.R
import android.support.annotation.StringRes

enum class Category(
        @StringRes val nameResId: Int,
        val networkPath: String
) {
    HOME(R.string.category_home, "home.json"),
    OPINION(R.string.category_opinion, "opinion.json"),
    WORLD(R.string.category_world, "world.json"),
    NATIONAL(R.string.category_national, "national.json"),
    POLITICS(R.string.category_politics, "politics.json"),
    UPSHOT(R.string.category_upshot, "upshot.json"),
    NY_REGION(R.string.category_nyregion, "nyregion.json"),
    BUSINESS(R.string.category_business, "business.json"),
    TECHNOLOGY(R.string.category_technology, "technology.json"),
    SCIENCE(R.string.category_science, "science.json"),
    HEALTH(R.string.category_health, "health.json"),
    SPORTS(R.string.category_sports, "sports.json"),
    ARTS(R.string.category_arts, "arts.json"),
    BOOKS(R.string.category_books, "books.json"),
    MOVIES(R.string.category_movies, "movies.json"),
    THEATER(R.string.category_theater, "theater.json"),
    SUNDAY_REVIEW(R.string.category_sunday_review, "sundayreview.json"),
    FASHION(R.string.category_fashion, "fashion.json"),
    T_MAGAZINE(R.string.category_t_magazine, "tmagazine.json"),
    FOOD(R.string.category_food, "food.json"),
    TRAVEL(R.string.category_travel, "travel.json"),
    MAGAZINE(R.string.category_magazine, "magazine.json"),
    REAL_ESTATE(R.string.category_real_estate, "realestate.json"),
    AUTOMOBILES(R.string.category_automobiles, "automobiles.json"),
    OBITUARIES(R.string.category_obituaries, "obituaries.json"),
    INSIDER(R.string.category_insider, "insider.json")
}