package android.academy.nytimes.network;

import android.academy.nytimes.data.NewsItem;
import android.academy.nytimes.mvp.RemoteRepository;
import android.academy.nytimes.network.model.NYResponseRoot;
import android.academy.nytimes.network.model.Result;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteRepositoryImpl implements RemoteRepository {

    private static final Map<String, String> categories = createMap();
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final RetrofitHelper retrofitHelper;

    public RemoteRepositoryImpl(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }

    @Override
    public List<NewsItem> getNews(String category) throws IOException {
        NYResponseRoot root = retrofitHelper.getNYTimesApi().getNews(categories.get(category), RetrofitHelper.TOKEN).execute().body();
        List<NewsItem> newsList = new ArrayList<>();
        for (Result result : root.getResults()) {
            newsList.add(new NewsItem(
                    result.getTitle(),
                    result.getMultimedia().isEmpty() ? null : result.getMultimedia().get(0).getUrl(),
                    result.getSubsection().isEmpty() ? category : result.getSubsection(),
                    getDateFromString(result.getPublishedDate().substring(0, result.getPublishedDate().indexOf('T'))),
                    result.get_abstract(),
                    result.getUrl()
            ));
        }
        return newsList;
    }

    @Override
    public List<String> getCategories() {
        return new ArrayList<>(categories.keySet());
    }

    private static Map<String, String> createMap() {
        Map<String, String> map = new HashMap<>();
        map.put("Home", "home.json");
        map.put("Opinion", "opinion.json");
        map.put("World", "world.json");
        map.put("National", "national.json");
        map.put("Politics", "politics.json");
        map.put("Upshot", "upshot.json");
        map.put("NYRegion", "nyregion.json");
        map.put("Business", "business.json");
        map.put("Technology", "technology.json");
        map.put("Science", "science.json");
        map.put("Health", "health.json");
        map.put("Sports", "sports.json");
        map.put("Arts", "arts.json");
        map.put("Books", "books.json");
        map.put("Movies", "movies.json");
        map.put("Theater", "theater.json");
        map.put("Sunday Review", "sundayreview.json");
        map.put("Fashion", "fashion.json");
        map.put("T-Magazine", "tmagazine.json");
        map.put("Food", "food.json");
        map.put("Travel", "travel.json");
        map.put("Magazine", "magazine.json");
        map.put("Real estate", "realestate.json");
        map.put("Automobiles", "automobiles.json");
        map.put("Obituaries", "obituaries.json");
        map.put("Insider", "insider.json");
        return map;
    }

    private Date getDateFromString(String s) {
        try {
            return dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date(Calendar.getInstance().getTimeInMillis());
        }
    }
}
