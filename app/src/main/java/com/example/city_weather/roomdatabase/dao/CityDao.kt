package com.example.city_weather.roomdatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.city_weather.roomdatabase.entity.CityEntity

@Dao
interface CityDao {
    @Query("SELECT * FROM CITIES order by id desc")
    fun getAllCity():LiveData<List<CityEntity>>

//    @Query("SELECT * FROM CITIES where id = id")
//    fun getById(id:Int): CityEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CityEntity)

    @Update
    suspend fun update(item: CityEntity)

    @Delete
    suspend fun delete(item: CityEntity)

    @Query("DELETE FROM CITIES")
    suspend fun deleteAllCities()
}