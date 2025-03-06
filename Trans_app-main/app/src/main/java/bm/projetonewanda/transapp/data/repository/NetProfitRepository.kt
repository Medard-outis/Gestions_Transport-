package bm.projetonewanda.transapp.data.repository

import bm.projetonewanda.transapp.data.dao.IncomeDao

class NetProfitRepository(private val incomeDao: IncomeDao) {

    suspend fun getDailyProfit(date: String): Double {
        return incomeDao.getDailyProfit(date) ?: 0.0
    }

    suspend fun getMonthlyProfit(month: String): Double {
        return incomeDao.getMonthlyProfit(month) ?: 0.0
    }
}
