package android.academy.nytimes.mvp;

import android.academy.nytimes.data.NewsItem;

import java.util.List;

public interface View {

    void showLoading();

    void showItems(List<NewsItem> items);

    void showError();
}
