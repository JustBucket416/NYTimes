package android.academy.nytimes.recycler;

import android.academy.nytimes.data.NewsItem;
import android.view.View;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ViewHolderBinder {

    private final HolderClickListener listener;

    public ViewHolderBinder(HolderClickListener listener) {
        this.listener = listener;
    }

    void bindViewHolder(NewsAdapter.NewsHolder holder, final NewsItem item) {
        if (item.getImageUrl() != null)
            Glide.with(holder.image.getContext())
                    .load(item.getImageUrl())
                    .into(holder.image);

        holder.content.setText(item.getPreviewText());
        holder.title.setText(item.getTitle());
        holder.category.setText(item.getCategory());
        holder.time.setText(SimpleDateFormat.getDateInstance().format(item.getPublishDate()));

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onHolderClicked(item.getUrl());
            }
        });
    }

    public interface HolderClickListener {

        void onHolderClicked(String url);
    }
}
