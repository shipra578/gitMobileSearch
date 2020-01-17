package com.shipra.android.gitmobilesearch.ui

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.shipra.android.gitmobilesearch.R
import com.shipra.android.gitmobilesearch.util.Constants
import kotlinx.android.synthetic.main.activity_web_view.*


class WebViewActivity : AppCompatActivity() {

    lateinit var mWebview: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        mWebview = webview
        mWebview.setWebViewClient(myWebViewClient());
        val url = intent.getStringExtra(Constants.KEY_WEB_URL);
        mWebview.loadUrl(url)
    }

    class myWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }
}
