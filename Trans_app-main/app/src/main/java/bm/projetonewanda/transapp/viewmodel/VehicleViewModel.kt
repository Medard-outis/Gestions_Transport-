package bm.projetonewanda.transapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bm.projetonewanda.transapp.data.model.Vehicle
import bm.projetonewanda.transapp.data.repository.VehicleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VehicleViewModel(private val vehicleRepository: VehicleRepository) : ViewModel() {

    private val _vehicles = MutableStateFlow<List<Vehicle>>(emptyList())
    val vehicles: StateFlow<List<Vehicle>> = _vehicles

    fun getAllVehicles() {
        viewModelScope.launch {
            vehicleRepository.getAllVehicles().collect {
                _vehicles.value = it
            }
        }
    }

    fun addVehicle(vehicle: Vehicle) {
        viewModelScope.launch {
            vehicleRepository.insert(vehicle)
            getAllVehicles() // Recharger la liste après insertion
        }
    }

    fun deleteVehicle(vehicle: Vehicle) {
        viewModelScope.launch {
            vehicleRepository.delete(vehicle)
            getAllVehicles() // Recharger la liste après suppression
        }
    }
}
