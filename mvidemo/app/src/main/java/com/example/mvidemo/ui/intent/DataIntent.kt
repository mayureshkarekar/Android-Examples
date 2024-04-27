package com.example.mvidemo.ui.intent

sealed class DataIntent {
    object FetchData : DataIntent()
}