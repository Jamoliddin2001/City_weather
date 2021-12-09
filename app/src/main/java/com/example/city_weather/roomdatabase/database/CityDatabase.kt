package com.example.city_weather.roomdatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.city_weather.roomdatabase.dao.CityDao
import com.example.city_weather.roomdatabase.entity.CityEntity

@Database(entities = [CityEntity::class], version = 1, exportSchema = false)
abstract class CityDatabase: RoomDatabase() {

    companion object{

        private var db: CityDatabase? = null
        private const val DB_NAME = "main.db"

        fun getInstance(context: Context): CityDatabase {
            synchronized(this){
               db?.let { return it }
                val instance = Room
                    .databaseBuilder(
                        context,
                        CityDatabase::class.java,
                        DB_NAME
                    ).build()
                db=instance
                return instance
            }
        }
    }

    abstract fun cityDao(): CityDao
}