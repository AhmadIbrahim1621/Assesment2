package org.d3if3091.Assesment2.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HexaDao {
    @Insert
    fun insert(tiket: HexaEntity)

    @Query("SELECT * FROM hexa ORDER BY id DESC")
    fun getLastHexa(): LiveData<List<HexaEntity>>

    @Query("DELETE FROM hexa")
    fun clearData()
}