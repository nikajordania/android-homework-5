package com.example.androidhomework5.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Distance::class], version = 1)
abstract class DistanceDatabase : RoomDatabase() {
    abstract fun distanceDao(): DistanceDao
}