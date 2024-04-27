package com.example.mvpdemo.ui

import com.example.mvpdemo.data.model.Quote
import com.example.mvpdemo.data.repository.MainRepository

class MainPresenter(private val view: IView) : IPresenter {
    private val repository = MainRepository(this)

    override fun fetchQuote() {
        view.showProgress()
        repository.fetchQuote()
    }

    override fun fetchQuoteSuccess(quote: Quote) {
        view.hideProgress()
        view.showQuote(quote)
    }
}