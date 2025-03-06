package bm.projetonewanda.transapp.data.repository

import bm.projetonewanda.transapp.data.dao.VehicleDao
import bm.projetonewanda.transapp.data.model.Vehicle
import kotlinx.coroutines.flow.Flow

class VehicleRepository(private val vehicleDao: VehicleDao) {

    suspend fun insert(vehicle: Vehicle) {
        vehicleDao.insert(vehicle)
    }

    fun getAllVehicles(): Flow<List<Vehicle>> {
        return vehicleDao.getAllVehicles()
    }

    suspend fun delete(vehicle: Vehicle) {
        vehicleDao.delete(vehicle)
    }
}
