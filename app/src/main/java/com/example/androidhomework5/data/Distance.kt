package com.example.androidhomework5.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Distance(
    @ColumnInfo(name = "run_distance") val runDistance: Int?,
    @ColumnInfo(name = "swim_distance") val swimDistance: Int?,
    @ColumnInfo(name = "calorie_taken") val calorieTaken: Int?
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}