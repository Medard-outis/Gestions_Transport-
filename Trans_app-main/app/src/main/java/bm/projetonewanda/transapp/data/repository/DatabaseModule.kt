package bm.projetonewanda.transapp.data.repository

import android.content.Context
import androidx.room.Room
import bm.projetonewanda.transapp.data.AppDatabase

object DatabaseModule {

    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "transapp_db"
            ).build()
        }
        return INSTANCE!!
    }
}
