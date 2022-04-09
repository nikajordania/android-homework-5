package com.example.androidhomework5.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DistanceDao {
    @Query("SELECT * FROM distance")
    fun getAll(): List<Distance>

    @Insert
    fun insertAll(vararg distance: Distance)
}