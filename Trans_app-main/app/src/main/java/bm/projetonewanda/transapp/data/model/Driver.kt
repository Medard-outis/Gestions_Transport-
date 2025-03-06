package bm.projetonewanda.transapp.data.model


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "drivers",
    foreignKeys = [
        ForeignKey(
            entity = Vehicle::class,
            parentColumns = ["id"],
            childColumns = ["vehicleId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("vehicleId")]
)
data class Driver(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val contact: String,
    val vehicleId: Int
)
