package com.example.mvidemo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvidemo.data.repository.MainRepository
import com.example.mvidemo.ui.intent.DataIntent
import com.example.mvidemo.ui.viewstate.DataState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class DataViewModel(private val repository: MainRepository) : ViewModel() {
    val dataIntent = Channel<DataIntent>(Channel.UNLIMITED)
    val dataState = MutableStateFlow<DataState>(DataState.Inactive)

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            dataIntent.consumeAsFlow().collect {
                when (it) {
                    DataIntent.FetchData -> fetchData()
                }
            }
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            dataState.value = DataState.Loading
            dataState.value = try {
                DataState.ResponseData(repository.getData())
            } catch (e: Exception) {
                DataState.Error(e.message.orEmpty())
            }
        }
    }
}