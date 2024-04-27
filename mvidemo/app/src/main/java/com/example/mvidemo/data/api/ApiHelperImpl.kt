package com.example.mvidemo.data.api

import com.example.mvidemo.data.model.Data

class ApiHelperImpl(private val apiService: APIService) : APIHelper {
    override suspend fun getData(): Data {
        return apiService.getData()
    }
}