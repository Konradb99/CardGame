package com.example.karcianka.Database.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.karcianka.GameEntity.ICard


@Entity(tableName="gameSaves")
data class GameSave(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0L,
    @ColumnInfo(name="checkpoint")
    var checkpoint: String = "0",
    @ColumnInfo(name="currentCard")
    var card: String
)