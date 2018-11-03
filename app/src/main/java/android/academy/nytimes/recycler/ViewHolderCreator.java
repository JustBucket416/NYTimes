package android.academy.nytimes.recycler;

import android.academy.nytimes.R;
import android.view.LayoutInflater;
import android.view.ViewGroup;


public class ViewHolderCreator {

    NewsAdapter.NewsHolder createViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new NewsAdapter.NewsHolder(inflater.inflate(R.layout.recycler_item, parent, false));
    }
}
