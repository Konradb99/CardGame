package com.example.karcianka.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.karcianka.Database.Entity.EquipmentItems
import com.example.karcianka.Database.Entity.GameSave

@Dao
interface DAO {

    //Equipment
    @Insert
    fun InsertItem(item: EquipmentItems)

    @Delete
    fun DeleteItem(item: EquipmentItems)

    @Query("SELECT * FROM eqTable")
    fun GetAllItems(): LiveData<List<EquipmentItems>>

    @Query("DELETE FROM eqTable")
    fun DeleteAllItems()

    //Game saves
    @Insert
    fun InsertSave(save: GameSave)

    @Query("SELECT * FROM gameSaves")
    fun GetAllSaves(): LiveData<List<GameSave>>
}