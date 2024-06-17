package com.example.mapsted.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mapsted.model.BuildingModel
import com.example.mapsted.model.DeviceModel
import com.example.mapsted.repository.BuildingRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class BuildingViewModel(private val repository: BuildingRepository) : ViewModel() {
    private val _buildings = MutableLiveData<List<BuildingModel>>()
    val buildings: LiveData<List<BuildingModel>> = _buildings

    private val _device = MutableLiveData<List<DeviceModel>>()
    val device: LiveData<List<DeviceModel>> = _device
    private val tag = "APILogger"

    init {
        loadBuildings()
    }

    private fun loadBuildings() {
        viewModelScope.launch {
            try {
                coroutineScope {
                    val buildingsDeferred = async { repository.getBuildings() }
                    val analyticsDeferred = async { repository.getAnaltic() }

                    val buildingsResponse = buildingsDeferred.await()
                    val analyticsResponse = analyticsDeferred.await()

                    when {
                        buildingsResponse.isNotEmpty() -> _buildings.postValue(buildingsResponse)
                        else -> Log.e(tag, "BUILDING---FALSE::")
                    }
                    when {
                        analyticsResponse.isNotEmpty() -> _device.postValue(analyticsResponse)
                        else -> Log.e(tag, "ANALYTICS---FALSE::")
                    }
                }
            } catch (e: Exception) {
                Log.e(tag, "EXCEPTION::" + e.message)
            }
        }
    }
}