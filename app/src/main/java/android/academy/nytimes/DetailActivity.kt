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

        fun newIntent(context: Context, url: String): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(URL_KEY, url)
            return intent
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val url = intent.getStringExtra(URL_KEY) ?: return

        webView.webViewClient = WebClient()
        val set = webView.settings
        set.javaScriptEnabled = true
        set.builtInZoomControls = true
        webView.loadUrl(url)
    }

    private inner class WebClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }

}
