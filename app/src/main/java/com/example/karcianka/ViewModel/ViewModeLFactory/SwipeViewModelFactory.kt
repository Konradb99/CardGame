package com.example.karcianka.ViewModel.ViewModeLFactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.karcianka.ViewModel.SwipeViewModel
import java.lang.IllegalArgumentException

class SwipeViewModelFactory(private val application: Application): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SwipeViewModel::class.java)){
            return SwipeViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}