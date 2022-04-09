package com.example.androidhomework5

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.androidhomework5.data.Distance
import com.example.androidhomework5.data.DistanceDatabase

class MainActivity : AppCompatActivity() {
    private val db = getDatabase()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val saveButton: Button = findViewById<View>(R.id.save_button) as Button



        saveButton.setOnClickListener {
            val distanceRecord = Distance(
                0,
                findViewById<EditText>(R.id.run_distance).text.toString().toInt(),
                findViewById<EditText>(R.id.swim_distance).text.toString().toInt(),
                findViewById<EditText>(R.id.calories_taken).text.toString().toInt()
            )

            db.distanceDao().insertAll(distanceRecord)
            avg()
        }
    }

    private fun avg() {
        val allRecords: List<Distance> = db.distanceDao().getAll()

        val fullRunDistance = allRecords.mapNotNull { it.runDistance }.sum().toString()
        val avgOfRunDistance = allRecords.mapNotNull { it.runDistance }.average().toString()
        val avgOfSwimDistance = allRecords.mapNotNull { it.swimDistance }.average().toString()
        val avgOfCalorieTaken = allRecords.mapNotNull { it.calorieTaken }.average().toString()

        findViewById<TextView>(R.id.run_distance_avg).text = avgOfRunDistance
        findViewById<TextView>(R.id.swim_distance_avg).text = avgOfSwimDistance
        findViewById<TextView>(R.id.calories_taken_avg).text = avgOfCalorieTaken

        findViewById<TextView>(R.id.run_distance_sum).text = fullRunDistance
    }

    private fun getDatabase(): DistanceDatabase {
        return Room.databaseBuilder(
            applicationContext,
            DistanceDatabase::class.java,
            "distance"
        ).build()
    }

}