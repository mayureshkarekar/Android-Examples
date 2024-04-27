package com.example.mvpdemo.data.repository

import com.example.mvpdemo.ui.MainPresenter
import com.example.mvpdemo.data.model.Quote

class MainRepository(private val presenter: MainPresenter) : IRepository {

    override fun fetchQuote() {
        val quotes = listOf(
            Quote("Be yourself; everyone else is already taken.", "Oscar Wilde"),
            Quote(
                "Two things are infinite: the universe and human stupidity; and I'm not sure about the universe.",
                "Albert Einstein"
            ),
            Quote("So many books, so little time.", "Frank Zappa")
        )

        val randomIndex = quotes.indices.random()
        presenter.fetchQuoteSuccess(quotes[randomIndex])
    }
}