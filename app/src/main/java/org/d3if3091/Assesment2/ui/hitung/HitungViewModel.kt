package org.d3if3091.Assesment2.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3091.Assesment2.db.HexaDao
import org.d3if3091.Assesment2.db.HexaEntity
import org.d3if3091.Assesment2.model.HasilHexa
import org.d3if3091.Assesment2.model.hitungHexa

class HitungViewModel (private val db: HexaDao) : ViewModel(){
    private val hasilHexa = MutableLiveData<HasilHexa?>()

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            UpdateWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
    fun hitungHexa(jumlah: Double) {
        val dataHexa = HexaEntity(
            jumlah = jumlah
        )
        hasilHexa.value = dataHexa.hitungHexa()

        viewModelScope.launch{
            withContext(Dispatchers.IO) {
                db.insert(dataHexa)
            }
        }
    }

    fun getHasilHexa(): LiveData<HasilHexa?> = hasilHexa
}