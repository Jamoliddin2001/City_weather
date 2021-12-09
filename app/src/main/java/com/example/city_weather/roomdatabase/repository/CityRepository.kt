package com.example.city_weather.roomdatabase.repository

import androidx.lifecycle.LiveData
import com.example.city_weather.roomdatabase.dao.CityDao
import com.example.city_weather.roomdatabase.entity.CityEntity

class CityRepository(private val cityDao: CityDao) {

    val readAllCity: LiveData<List<CityEntity>> = cityDao.getAllCity()

//    suspend fun getById(index:Int){
//        cityDao.getById(index)
//    }

    suspend fun addCity(item: CityEntity){
        cityDao.insert(item)
    }

    suspend fun updateCity(item: CityEntity){
        cityDao.update(item)
    }

    suspend fun deleteCity(item:CityEntity){
        cityDao.delete(item)
    }

    suspend fun deleteAllCity(){
        cityDao.deleteAllCities()
    }
}