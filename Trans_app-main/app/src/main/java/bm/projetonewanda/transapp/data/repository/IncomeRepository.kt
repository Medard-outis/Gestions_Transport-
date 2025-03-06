package bm.projetonewanda.transapp.data.repository

import bm.projetonewanda.transapp.data.dao.IncomeDao
import bm.projetonewanda.transapp.data.model.Income
import kotlinx.coroutines.flow.Flow

class IncomeRepository(private val incomeDao: IncomeDao) {

    suspend fun insert(income: Income) {
        incomeDao.insert(income)
    }

    fun getIncomesByVehicle(vehicleId: Int): Flow<List<Income>> {
        return incomeDao.getIncomesByVehicle(vehicleId)
    }

    suspend fun delete(income: Income) {
        incomeDao.delete(income)
    }

    fun getAllIncomes(): Flow<List<Income>> {
        return incomeDao.getAllIncomes()
    }
}


