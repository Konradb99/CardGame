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

        supportFragmentManager.beginTransaction().add(R.id.main_fragment, fragment_menu(), "").commit()
        LocNav.ConnectLocGrapf()



        //Temponary items
        //Remove on release

        val factoryEquipmentViewModel = EquipmentViewModelFactory((requireNotNull(this).application))
        val eqVM = ViewModelProvider(this, factoryEquipmentViewModel).get(
            EquipmentViewModel::class.java)
        //Temponary list of items
        eqVM.addItem(EquipmentItems(0, 0 ,"Losos", R.drawable.eq_back, R.drawable.eq_back))
        eqVM.addItem(EquipmentItems(0, 0 ,"UTM", R.drawable.eq_back, R.drawable.eq_back))
        eqVM.addItem(EquipmentItems(0, 0 ,"Mrowka", R.drawable.eq_back, R.drawable.eq_back))
        eqVM.addItem(EquipmentItems(0, 0 ,"", R.drawable.eq_back, R.drawable.eq_back))
    }
}