package bm.projetonewanda.transapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicles")
data class Vehicle(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val plaque: String,
    val model: String,
    val type: String, // taxi, moto, bus
    val status: String // entretien, panne, assurance
)