package com.example.karcianka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.karcianka.Database.Entity.EquipmentItems
import com.example.karcianka.Model.LocNav
import com.example.karcianka.ViewModel.EquipmentViewModel
import com.example.karcianka.ViewModel.ViewModeLFactory.EquipmentViewModelFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}