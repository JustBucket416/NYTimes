package android.academy.nytimes.mvp;

import android.academy.nytimes.data.NewsItem;

import java.io.IOException;
import java.util.List;

public class Model {

    private final RemoteRepository remoteRepository;

    public Model(RemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    List<NewsItem> loadNews(String category) throws IOException {
        return remoteRepository.getNews(category);
    }

    List<String> getCategories() {
        return remoteRepository.getCategories();
    }


}
