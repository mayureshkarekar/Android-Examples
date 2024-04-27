package com.example.mvidemo.ui.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvidemo.data.api.APIHelper
import com.example.mvidemo.data.repository.MainRepository
import com.example.mvidemo.ui.viewmodel.DataViewModel

@Suppress("UNCHECKED_CAST")
class DataViewModelFactory(private val apiHelper: APIHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DataViewModelFactory::class.java)) {
            return DataViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}