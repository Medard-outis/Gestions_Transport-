package bm.projetonewanda.transapp.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import bm.projetonewanda.transapp.data.dao.DriverDao
import bm.projetonewanda.transapp.data.dao.IncomeDao
import bm.projetonewanda.transapp.data.dao.VehicleDao
import bm.projetonewanda.transapp.data.model.Driver
import bm.projetonewanda.transapp.data.model.Income
import bm.projetonewanda.transapp.data.model.Vehicle



@Database(entities = [Vehicle::class, Driver::class, Income::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
    abstract fun driverDao(): DriverDao
    abstract fun incomeDao(): IncomeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "transapp_db"
                )
                    .fallbackToDestructiveMigration() // Permet de supprimer la base si elle est corrompue
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}


