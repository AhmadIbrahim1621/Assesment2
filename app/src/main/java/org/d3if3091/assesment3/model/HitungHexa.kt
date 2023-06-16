package org.d3if3091.assesment3.model

import org.d3if3091.assesment3.db.HexaEntity
import kotlin.math.pow

fun HexaEntity.hitungHexa(): HasilHexa {
    val sqrt3 = 1.732
    val volume = (2 * sqrt3 * jumlah.pow(3)) / 3

    return HasilHexa(volume)
}