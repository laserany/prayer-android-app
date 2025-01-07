package com.example.prayer

import android.os.Bundle
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        run("https://api.aladhan.com/v1/timingsByAddress?address=9685+Pinehurst+Street%2C+Detroit%2C+MI")
    }


    fun run(url: String) {
        val objectMapper = jacksonObjectMapper()
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("hello")
                println("why")
            }
            override fun onResponse(call: Call, response: Response) {
                val prayerDTO:PrayerDTO = objectMapper.readValue(response.body()?.string()?: "")
                System.out.println(prayerDTO)
                val timings = prayerDTO.data.timings
                addRowToTableLayout(timings.Isha, timings.Maghrib, timings.Asr, timings.Dhuhr, timings.Fajr)
            }
        })
    }

    fun addRowToTableLayout(isha: String, maghrib: String, asr: String, dhuhr: String, fajr: String) {
        val tableLayout = findViewById<TableLayout>(R.id.tableLayout)
        val newRow = TableRow(this)
        newRow.setPadding(5)
        newRow.setBackgroundColor(ContextCompat.getColor(this,R.color.white))
        addTextViewToNewRow(isha, newRow)
        addTextViewToNewRow(maghrib, newRow)
        addTextViewToNewRow(asr, newRow)
        addTextViewToNewRow(dhuhr, newRow)
        addTextViewToNewRow(fajr, newRow)
        runOnUiThread {
            tableLayout.addView(newRow)
        }
    }

    fun addTextViewToNewRow(text: String, newRow: TableRow) {
        val textView = TextView(this)
        val filteredText = text.replace("%am%", "am").replace("%pm%", "pm")
        textView.text = filteredText
        textView.setPadding(80,0,0,0)
        textView.setTextColor(ContextCompat.getColor(this,R.color.black))
        textView.gravity = Gravity.CENTER
        newRow.addView(textView)
    }

}
