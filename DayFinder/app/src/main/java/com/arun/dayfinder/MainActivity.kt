package com.arun.dayfinder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arun.dayfinder.Day.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todayDate.text = Date().toLocaleString()
        "Day of the week: ${dayFinder(Date().day)}".also { todayDay.text = it }

        find.setOnClickListener { v ->
            val date = dateText.text.toString().toInt()
            val month = monthText.text.toString().toInt()
            val year = yearText.text.toString().toInt()

            if (month <= 12 && date <= 31) {
                "Date: $date / $month / $year".also {
                    todayDate.text = it
                }
                "Day of the week: ${dayFinderFormula(year, month, date)}".also {
                    todayDay.text = it
                }
            } else {
                Snackbar.make(v, "Enter a valid Date...", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Clear Fields") {
                        dateText.text.clear()
                        monthText.text.clear()
                        yearText.text.clear()
                    }.show()
            }
        }
    }

    private fun dayFinder(day: Int): String {
        return when (day) {
            Sunday.idx -> Sunday.name
            Monday.idx -> Monday.name
            Tuesday.idx -> Tuesday.name
            Wednesday.idx -> Wednesday.name
            Thursday.idx -> Thursday.name
            Friday.idx -> Friday.name
            Saturday.idx -> Saturday.name
            else -> ""
        }
    }


    private fun dayFinderFormula(y: Int, m: Int, d: Int): String {
        val t = arrayOf(
            0, 3, 2, 5, 0, 3,
            5, 1, 4, 6, 2, 4
        )
        var year = y
        year -= if (m < 3) 1 else 0
        val day: Int = ((year + (year / 4) - (year / 100)
                + (year / 400) + t[m - 1] + d) % 7)

        return dayFinder(day)
    }
}