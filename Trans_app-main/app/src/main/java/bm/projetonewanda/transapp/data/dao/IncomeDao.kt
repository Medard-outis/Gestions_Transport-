package bm.projetonewanda.transapp.data.dao



import androidx.room.*
import bm.projetonewanda.transapp.data.model.Income
import kotlinx.coroutines.flow.Flow

@Dao
interface IncomeDao {
    @Insert
    suspend fun insert(income: Income)

    @Query("SELECT * FROM Income WHERE vehicleId = :vehicleId ORDER BY date DESC")
    fun getIncomesByVehicle(vehicleId: Int): Flow<List<Income>>

    @Delete
    suspend fun delete(income: Income)

    @Query("SELECT * FROM income")
    fun getAllIncomes(): Flow<List<Income>>

    @Query("SELECT SUM(amount) FROM income WHERE date = :date")
    suspend fun getDailyProfit(date: String): Double?

    @Query("SELECT SUM(amount) FROM income WHERE strftime('%Y-%m', date) = :month")
    suspend fun getMonthlyProfit(month: String): Double?


}

