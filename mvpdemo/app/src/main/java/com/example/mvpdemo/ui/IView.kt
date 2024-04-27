package com.example.mvpdemo.ui

import com.example.mvpdemo.data.model.Quote

interface IView {
    fun showProgress()
    fun hideProgress()
    fun showQuote(quote: Quote)
}