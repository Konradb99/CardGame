package com.example.karcianka.Database.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.karcianka.R


@Entity(tableName = "eqTable")
data class EquipmentItems(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0L,
    @ColumnInfo(name = "saveID")
    var saveID: Long = 0L,
    @ColumnInfo(name = "itemName")
    var name: String = "Unknown item",
    @ColumnInfo(name = "itemBackground")
    var background: Int = R.drawable.eq_back,
    @ColumnInfo(name = "itemBackgroundBack")
    var backgroundBack: Int = R.drawable.eq_back
)