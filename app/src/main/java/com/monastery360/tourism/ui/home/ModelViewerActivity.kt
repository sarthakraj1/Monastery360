//package com.monastery360.tourism.ui.home
//
//import android.os.Bundle
//import android.webkit.WebSettings
//import android.webkit.WebView
//import android.webkit.WebViewClient
//import androidx.appcompat.app.AppCompatActivity
//import com.monastery360.tourism.R
//
//class ModelViewerActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.glb_model_viewer)
//
//        val webView: WebView = findViewById(R.id.web_view)
//
//        val settings: WebSettings = webView.settings
//        settings.javaScriptEnabled = true
//        settings.allowFileAccess = true
//        settings.allowFileAccessFromFileURLs = true
//        settings.allowUniversalAccessFromFileURLs = true
//
//        webView.webViewClient = WebViewClient()
//
//        webView.loadUrl("https://appassets.androidplatform.net/app/src/main/assets/model_viewer.html")
//
//    }
//}


package com.monastery360.tourism.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebViewClient
import androidx.webkit.WebViewAssetLoader
import androidx.activity.ComponentActivity

class ModelViewerActivity : ComponentActivity() {
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webView = WebView(this)
        webView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        setContentView(webView)

        val settings: WebSettings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.mediaPlaybackRequiresUserGesture = false
        settings.allowFileAccess = true
        settings.allowContentAccess = true
        settings.allowFileAccessFromFileURLs = true
        settings.allowUniversalAccessFromFileURLs = true

        WebView.setWebContentsDebuggingEnabled(true)
        val assetLoader = WebViewAssetLoader.Builder()
            .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(this))
            .build()

        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {
            override fun shouldInterceptRequest(view: WebView, request: WebResourceRequest): WebResourceResponse? {
                val path = request.url.encodedPath ?: ""
                return if (path.endsWith("/assets/model.glb")) {
                    // Force correct content type; some devices need the exact mime
                    WebResourceResponse(
                        "model/gltf-binary",
                        null,
                        assets.open("model.glb")
                    )
                } else {
                    assetLoader.shouldInterceptRequest(request.url)
                }
            }
        }

        webView.loadUrl("https://appassets.androidplatform.net/assets/model_viewer.html")
    }

    override fun onDestroy() {
        (webView.parent as? ViewGroup)?.removeView(webView)
        webView.destroy()
        super.onDestroy()
    }
}



