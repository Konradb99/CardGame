package com.example.karcianka.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.karcianka.Database.Entity.EquipmentItems

@Dao
interface DAO {
    @Insert
    fun InsertItem(item: EquipmentItems)

    @Delete
    fun DeleteItem(item: EquipmentItems)

    @Query("SELECT * FROM eqTable")
    fun GetAllItems(): LiveData<List<EquipmentItems>>

    @Query("DELETE FROM eqTable")
    fun DeleteAllItems()
}