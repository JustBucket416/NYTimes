package android.academy.nytimes.mvp;

import android.academy.nytimes.data.NewsItem;

import java.io.IOException;
import java.util.List;

public class Presenter {

    private final Model model;
    private View view;
    private List<NewsItem> news;
    private String category;

    public Presenter(Model model) {
        this.model = model;
    }

    public void attachView(View view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }

    public void fetchNews(final String category) {
        view.showLoading();
        if (category.equals(this.category)) {
            view.showItems(news);
        } else new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<NewsItem> list = model.loadNews(category);
                    news = list;
                    Presenter.this.category = category;
                    view.showItems(list);
                } catch (IOException e) {
                    view.showError();
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public List<String> loadCategories() {
        return model.getCategories();
    }
}
