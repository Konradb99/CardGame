package com.example.karcianka.ViewModel.ViewModeLFactory

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.karcianka.ViewModel.CardViewModel
import com.example.karcianka.ViewModel.EquipmentViewModel
import com.example.karcianka.ViewModel.GameViewModel
import java.lang.IllegalArgumentException

class GameViewModelFactory(private val application: Application, private val cardVM: CardViewModel, private val context: Context): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GameViewModel::class.java)){
            return GameViewModel(application, cardVM, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}