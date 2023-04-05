package com.superdemo.code.core.utils

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.superdemo.code.databinding.ActivityWebViewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WebViewActivity : AppCompatActivity() {
    private var _binding: ActivityWebViewBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var internetChecker: InternetChecker

    var webUrl: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

       /* binding.customToolbar.txtTitle.text = intent.getBundleExtra("bundle")?.getString("title")
        binding.customToolbar.imgClose.setOnClickListener {
            finish()
        }*/

        webUrl = /*intent.getStringExtra("webUrl")!!*/ intent.getBundleExtra("bundle")?.getString("webUrl")!!
        if (webUrl.contains("https://") || webUrl.contains("http://")) {
            setView(webUrl)
        } else {
            webUrl = "https://${webUrl}"
            setView(webUrl)
        }

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setView(url: String) {
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true
        loadUrl(url)
    }

    private fun loadUrl(url: String) {
        if (internetChecker.hasInternetConnection()) {
            binding.webView.loadUrl(url)
        } else {
            showToast(Constants.NO_INTERNET)
        }
    }

}