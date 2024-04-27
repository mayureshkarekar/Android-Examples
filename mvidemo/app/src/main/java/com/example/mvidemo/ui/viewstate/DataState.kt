package com.example.mvidemo.ui.viewstate

import com.example.mvidemo.data.model.Data

sealed class DataState {
    object Inactive : DataState()
    object Loading : DataState()
    data class Error(val message: String) : DataState()
    data class ResponseData(val data: Data) : DataState()
}