package bm.projetonewanda.transapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bm.projetonewanda.transapp.data.repository.DriverRepository

class DriverViewModelFactory(private val driverRepository: DriverRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DriverViewModel::class.java)) {
            return DriverViewModel(driverRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
