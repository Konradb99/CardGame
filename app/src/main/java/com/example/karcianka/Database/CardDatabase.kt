package com.example.karcianka.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.karcianka.Database.Entity.EquipmentItems

@Database(entities=[EquipmentItems::class], version = 1, exportSchema = true)
abstract class CardDatabase: RoomDatabase() {
    abstract val dao: DAO

    companion object {
        @Volatile
        private var INSTANCE: CardDatabase? = null
        fun getInstance(context: Context): CardDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null ){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CardDatabase::class.java,
                        "CardGame_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}