package com.example.androidhomework5

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidhomework5.data.Distance
import com.example.androidhomework5.data.DistanceDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val saveButton = findViewById<Button>(R.id.save_button)

        saveButton.setOnClickListener {
            val distanceRecord = Distance(
                findViewById<EditText>(R.id.run_distance).text.toString().toInt(),
                findViewById<EditText>(R.id.swim_distance).text.toString().toInt(),
                findViewById<EditText>(R.id.calories_taken).text.toString().toInt()
            )

            saveDistance(distanceRecord)
            avg()
        }
    }


    private fun saveDistance(distance: Distance) {
        class SaveDistance : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg p0: Void?): Void? {
                DistanceDatabase(applicationContext!!).distanceDao().insertAll(distance)
                return null
            }

            override fun onPostExecute(result: Void?) {
                Toast.makeText(applicationContext, "Distance Save", Toast.LENGTH_LONG).show()
            }
        }
        SaveDistance().execute()
    }

    private fun avg() {
        Thread(Runnable {
            val allRecords: List<Distance> =
                DistanceDatabase(applicationContext!!).distanceDao().getAll()

            runOnUiThread {
                val fullRunDistance = allRecords.mapNotNull { it.runDistance }.sum().toString()
                val avgOfRunDistance = allRecords.mapNotNull { it.runDistance }.average().toString()
                val avgOfSwimDistance = allRecords.mapNotNull { it.swimDistance }.average().toString()
                val avgOfCalorieTaken = allRecords.mapNotNull { it.calorieTaken }.average().toString()

                findViewById<TextView>(R.id.run_distance_avg).text = avgOfRunDistance
                findViewById<TextView>(R.id.swim_distance_avg).text = avgOfSwimDistance
                findViewById<TextView>(R.id.calories_taken_avg).text = avgOfCalorieTaken

                findViewById<TextView>(R.id.run_distance_sum).text = fullRunDistance
            }
        }).start()
    }
}