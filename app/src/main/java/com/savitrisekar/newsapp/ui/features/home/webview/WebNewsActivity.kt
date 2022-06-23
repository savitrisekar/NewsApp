package com.savitrisekar.newsapp.ui.features.home.webview

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.savitrisekar.newsapp.R
import com.savitrisekar.newsapp.data.model.Article
import com.savitrisekar.newsapp.databinding.ActivityWebNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebNewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebNewsBinding
    lateinit var viewModel: WebNewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[WebNewsViewModel::class.java]

        initView()
    }

    private fun initView() {
        val article: Article? = intent.getParcelableExtra("article")

        binding.newsWebview.apply {
            webViewClient = WebViewClient()
            loadUrl(article?.url!!)
        }

        binding.floatingActionButton.setOnClickListener {
            binding.floatingActionButton.setImageResource(R.drawable.ic_baseline_favorite)
            viewModel.insertNewsFavorite(article!!)
            Snackbar.make(
                binding.coordinatorWebView,
                getString(R.string.label_saved),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}