package com.example.mapsted.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mapsted.repository.BuildingRepository

class BuildingViewModelFactory(private val repository: BuildingRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BuildingViewModel::class.java)) {
            BuildingViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
