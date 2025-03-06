package bm.projetonewanda.transapp.data.repository

import bm.projetonewanda.transapp.data.dao.DriverDao
import bm.projetonewanda.transapp.data.model.Driver
import kotlinx.coroutines.flow.Flow

class DriverRepository(private val driverDao: DriverDao) {

    suspend fun insert(driver: Driver) {
        driverDao.insert(driver)
    }

    fun getAllDrivers(): Flow<List<Driver>> {
        return driverDao.getAllDrivers()
    }

    suspend fun delete(driver: Driver) {
        driverDao.delete(driver)
    }
}
