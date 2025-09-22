package com.monastery360.tourism.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.webkit.WebViewAssetLoader
import com.monastery360.tourism.databinding.GlbModelViewerBinding

class GLBModelViewer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: GlbModelViewerBinding
    private var isModelLoaded = false

    init {
        binding = GlbModelViewerBinding.inflate(LayoutInflater.from(context), this, true)
        setupWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        binding.apply {
            webView.settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                allowFileAccess = true
                allowContentAccess = true
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }

            WebView.setWebContentsDebuggingEnabled(true)

            val assetLoader = WebViewAssetLoader.Builder()
                .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(context))
                .build()

            webView.webChromeClient = WebChromeClient()
            webView.webViewClient = object : WebViewClient() {
                override fun shouldInterceptRequest(
                    view: WebView,
                    request: WebResourceRequest
                ): WebResourceResponse? {
                    val path = request.url.encodedPath ?: ""
                    return if (path.endsWith("/assets/models/model.glb")) {
                        WebResourceResponse(
                            "model/gltf-binary",
                            null,
                            context.assets.open("model.glb")
                        )
                    } else {
                        assetLoader.shouldInterceptRequest(request.url)
                    }
                }
            }
        }
    }

    /**
     * Load the HTML that contains the 3D model viewer
     */
    fun loadModel() {
        if (isModelLoaded) return
        binding.webView.loadUrl("https://appassets.androidplatform.net/assets/modelviewer.html")
        isModelLoaded = true
    }
}
