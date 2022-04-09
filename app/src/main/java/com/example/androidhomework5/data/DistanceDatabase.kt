package com.example.androidhomework5.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Distance::class], version = 1)
abstract class DistanceDatabase : RoomDatabase() {
    abstract fun distanceDao(): DistanceDao

    companion object {
        @Volatile
        private var instance: DistanceDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DistanceDatabase::class.java,
            "distancedatabase"
        ).build()
    }
}