package bm.projetonewanda.transapp.data.dao


import androidx.room.*
import bm.projetonewanda.transapp.data.model.Vehicle
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vehicle: Vehicle)

    @Query("SELECT * FROM vehicles")
    fun getAllVehicles(): Flow<List<Vehicle>>

    @Delete
    suspend fun delete(vehicle: Vehicle)
}
