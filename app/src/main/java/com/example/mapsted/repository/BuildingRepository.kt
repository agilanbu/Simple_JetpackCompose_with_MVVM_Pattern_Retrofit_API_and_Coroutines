package com.example.mapsted.repository

import com.example.mapsted.retrofit.ApiService

class BuildingRepository(private val api: ApiService) {
    suspend fun getBuildings() = api.getBuildingData()

    suspend fun getAnaltic() = api.getGetAnalyticData()
}