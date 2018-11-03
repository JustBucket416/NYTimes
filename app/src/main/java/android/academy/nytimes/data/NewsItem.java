package android.academy.nytimes.data;

import android.support.annotation.Nullable;

import java.util.Date;

public class NewsItem {

    private final String title;
    @Nullable
    private final String imageUrl;
    private final String category;
    private final Date publishDate;
    private final String previewText;
    private final String url;

    public NewsItem(String title, @Nullable String imageUrl, String category, Date publishDate, String previewText, String url) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.category = category;
        this.publishDate = publishDate;
        this.previewText = previewText;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public String getPreviewText() {
        return previewText;
    }

    public String getUrl() {
        return url;
    }

}
