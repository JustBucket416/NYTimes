package android.academy.nytimes;

import android.academy.nytimes.data.NewsItem;
import android.academy.nytimes.mvp.Presenter;
import android.academy.nytimes.recycler.NewsAdapter;
import android.academy.nytimes.recycler.ViewHolderBinder;
import android.academy.nytimes.recycler.ViewHolderCreator;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements android.academy.nytimes.mvp.View,
        ViewHolderBinder.HolderClickListener {

    private static final int MARGIN = 4;
    private static final int SPAN_COUNT = 2;
    private RecyclerView recyclerView;
    private AlertDialog dialog;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = ((NYApp) getApplicationContext()).getPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(
                getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE
                        ? new GridLayoutManager(this, SPAN_COUNT)
                        : new LinearLayoutManager(this)
        );

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect,
                                       @NonNull View view,
                                       @NonNull RecyclerView parent,
                                       @NonNull RecyclerView.State state) {
                outRect.left = MARGIN;
                outRect.bottom = MARGIN;
            }
        });

        presenter.attachView(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem menuItem = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) menuItem.getActionView();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, presenter.loadCategories());
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.fetchNews(presenter.loadCategories().get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        dialog.dismiss();
        presenter.detachView();
    }

    @Override
    public void onHolderClicked(String url) {
        startActivity(DetailActivity.newIntent(this, url));
    }

    @Override
    public void showLoading() {
        dialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setView(createProgressBar())
                .setTitle(R.string.please_wait)
                .show();
    }

    @Override
    public void showItems(final List<NewsItem> items) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(new NewsAdapter(items,
                        new ViewHolderBinder(MainActivity.this),
                        new ViewHolderCreator()));
                dialog.dismiss();
            }
        });
    }

    @Override
    public void showError() {
        //TODO setup for error
    }

    private View createProgressBar() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setIndeterminate(true);
        layout.addView(progressBar);

        TextView textView = new TextView(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(lp);
        textView.setText(R.string.loading);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        layout.addView(textView);

        return layout;
    }
}
