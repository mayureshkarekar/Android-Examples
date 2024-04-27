package com.example.mvpdemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.mvpdemo.databinding.ActivityMainBinding
import com.example.mvpdemo.data.model.Quote

class MainActivity : AppCompatActivity(), IView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainPresenter(this)
        presenter.fetchQuote()
    }

    override fun showProgress() {
        binding.progressQuote.isVisible = true
    }

    override fun hideProgress() {
        binding.progressQuote.isVisible = false
    }

    override fun showQuote(quote: Quote) {
        binding.apply {
            tvQuote.text = quote.quote
            tvAuthor.text = quote.author
        }
    }
}