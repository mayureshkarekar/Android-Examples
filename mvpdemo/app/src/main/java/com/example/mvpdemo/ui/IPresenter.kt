package com.example.mvpdemo.ui

import com.example.mvpdemo.data.model.Quote

interface IPresenter {
    fun fetchQuote()
    fun fetchQuoteSuccess(quote: Quote)
}