package com.example.mvidemo.data.api

import com.example.mvidemo.data.model.Data

interface APIHelper {
    suspend fun getData(): Data
}