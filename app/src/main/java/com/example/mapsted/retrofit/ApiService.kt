package com.example.mapsted.retrofit

import com.example.mapsted.model.BuildingModel
import com.example.mapsted.model.DeviceModel
import retrofit2.http.GET

interface ApiService {
    @GET("/GetBuildingData/")
    suspend fun getBuildingData(): List<BuildingModel>

    @GET("/GetAnalyticData/")
    suspend fun getGetAnalyticData(): List<DeviceModel>
}