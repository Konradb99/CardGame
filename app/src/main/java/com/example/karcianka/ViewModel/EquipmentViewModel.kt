package com.example.karcianka.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.karcianka.Database.CardDatabase
import com.example.karcianka.Database.DAO
import com.example.karcianka.Database.Entity.EquipmentItems
import com.example.karcianka.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EquipmentViewModel(application: Application): AndroidViewModel(application) {
    val item:EquipmentItems
    val allItems: LiveData<List<EquipmentItems>>
    private val dao : DAO
    init{
        dao = CardDatabase.getInstance(application).dao
        item = EquipmentItems(0, 0, "", R.drawable.eq_back, R.drawable.eq_back)
        allItems = dao.GetAllItems()
    }

    fun addItem(item: EquipmentItems){
        viewModelScope.launch(Dispatchers.IO) {
            dao.InsertItem(item)
        }
    }

    fun deleteItem(item :EquipmentItems)
    {
        viewModelScope.launch(Dispatchers.IO) {
            dao.DeleteItem(item)
        }
    }

    fun deleteAllItems()
    {
        viewModelScope.launch(Dispatchers.IO) {
        dao.DeleteAllItems()
    }
    }

}