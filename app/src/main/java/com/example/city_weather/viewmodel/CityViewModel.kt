package com.example.city_weather.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.city_weather.roomdatabase.database.CityDatabase
import com.example.city_weather.roomdatabase.entity.CityEntity
import com.example.city_weather.roomdatabase.repository.CityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CityViewModel(application: Application) : AndroidViewModel(application) {

    val readAllCity: LiveData<List<CityEntity>>
    private val repository: CityRepository

    init {
        val cityDao = CityDatabase.getInstance(application).cityDao()
        repository = CityRepository(cityDao = cityDao)
        readAllCity = repository.readAllCity
    }

    fun addCity(item: CityEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCity(item)
        }
    }

//    fun getById(index:Int){
//        viewModelScope.launch(Dispatchers.IO){
//            repository.getById(index)
//        }
//    }

    fun updateCity(item: CityEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCity(item)
        }
    }

    fun deleteCity(item: CityEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCity(item)
        }
    }

    fun deleteAllCity() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllCity()
        }
    }

}

class CityViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityViewModel::class.java)) {
            return CityViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}