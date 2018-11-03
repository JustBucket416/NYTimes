package android.academy.nytimes.mvp;

import android.academy.nytimes.data.NewsItem;

import java.io.IOException;
import java.util.List;

public interface RemoteRepository {

    List<NewsItem> getNews(String category) throws IOException;

    List<String> getCategories();
}
