package org.d3if3091.assesment3.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HexaEntity::class], version = 1, exportSchema = false)
abstract class HexaDb: RoomDatabase(){
    abstract val dao: HexaDao

    companion object {
        @Volatile
        private var INSTANCE: HexaDb? = null

        fun getInstance(context: Context): HexaDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HexaDb::class.java,
                        "hexa.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}