package com.example.karcianka.ViewModel.ViewModeLFactory

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.karcianka.ViewModel.CardViewModel
import java.lang.IllegalArgumentException

class CardViewModelFactory(private val application: Application, private val context: Context): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CardViewModel::class.java)){
            return CardViewModel(application, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}