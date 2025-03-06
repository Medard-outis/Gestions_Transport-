package bm.projetonewanda.transapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bm.projetonewanda.transapp.data.repository.VehicleRepository

class VehicleViewModelFactory(private val vehicleRepository: VehicleRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VehicleViewModel::class.java)) {
            return VehicleViewModel(vehicleRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
