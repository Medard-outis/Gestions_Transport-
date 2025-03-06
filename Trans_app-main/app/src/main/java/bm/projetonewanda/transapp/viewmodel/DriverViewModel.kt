package bm.projetonewanda.transapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bm.projetonewanda.transapp.data.model.Driver
import bm.projetonewanda.transapp.data.repository.DriverRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DriverViewModel(private val driverRepository: DriverRepository) : ViewModel() {

    private val _drivers = MutableStateFlow<List<Driver>>(emptyList())
    val drivers: StateFlow<List<Driver>> = _drivers

    fun getAllDrivers() {
        viewModelScope.launch {
            driverRepository.getAllDrivers().collect {
                _drivers.value = it
            }
        }
    }

    fun addDriver(driver: Driver) {
        viewModelScope.launch {
            driverRepository.insert(driver)
        }
    }

    fun deleteDriver(driver: Driver) {
        viewModelScope.launch {
            driverRepository.delete(driver)
        }
    }
}
