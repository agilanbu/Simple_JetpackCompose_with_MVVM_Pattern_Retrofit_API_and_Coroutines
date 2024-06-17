package com.example.mapsted

import BuildCompose
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.mapsted.repository.BuildingRepository
import com.example.mapsted.retrofit.RetrofitInstance
import com.example.mapsted.viewmodel.BuildingViewModel
import com.example.mapsted.viewmodel.BuildingViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var buildingViewModel: BuildingViewModel
    private lateinit var repository: BuildingRepository
    private lateinit var viewModelFactory: BuildingViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = BuildingRepository(RetrofitInstance.api)
        viewModelFactory = BuildingViewModelFactory(repository)
        buildingViewModel = ViewModelProvider(this, viewModelFactory)[BuildingViewModel::class.java]

        setContent {
            BuildCompose(buildingViewModel = buildingViewModel)
        }
    }
}

