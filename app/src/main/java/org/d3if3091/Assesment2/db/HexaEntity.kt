package org.d3if3091.Assesment2.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hexa")
data class HexaEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var jumlah: Double
)
