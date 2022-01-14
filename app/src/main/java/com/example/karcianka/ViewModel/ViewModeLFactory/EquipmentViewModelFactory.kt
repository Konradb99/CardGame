package com.example.karcianka.ViewModel.ViewModeLFactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.karcianka.ViewModel.EquipmentViewModel
import com.example.karcianka.ViewModel.SwipeViewModel
import java.lang.IllegalArgumentException

class EquipmentViewModelFactory(private val application: Application): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EquipmentViewModel::class.java)){
            return EquipmentViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}