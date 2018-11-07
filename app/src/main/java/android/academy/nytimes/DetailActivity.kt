package android.academy.nytimes

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        private const val URL_KEY = "url-key"

        fun newIntent(context: Context, url: String) =
                Intent(context, DetailActivity::class.java).apply {
                    putExtra(URL_KEY, url)
                }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val url = intent.getStringExtra(URL_KEY) ?: return

        webView.webViewClient = WebClient()
        webView.settings.run {
            javaScriptEnabled = true
            builtInZoomControls = true
        }
        webView.loadUrl(url)
    }

    private class WebClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }

}
