package com.example.mvidemo.data.repository

import com.example.mvidemo.data.api.APIHelper

class MainRepository(private val apiHelper: APIHelper) {
    suspend fun getData() = apiHelper.getData()
}