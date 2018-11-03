package android.academy.nytimes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DetailActivity extends AppCompatActivity {

    private static final String URL_KEY = "url-key";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onStart() {
        super.onStart();

        String url = getIntent().getStringExtra(URL_KEY);
        if (url == null) return;

        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebClient());
        WebSettings set = webView.getSettings();
        set.setJavaScriptEnabled(true);
        set.setBuiltInZoomControls(true);
        webView.loadUrl(url);

    }

    public static final Intent newIntent(Context context, String url) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(URL_KEY, url);
        return intent;
    }

    class WebClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
