package yhr.jfj.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    // Date picker
    private var tvSelectedDate: TextView? = null

    // Show in minute
    private var tvAgeInMinute: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Button
        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        // Date picker
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        // Show in minute
        tvAgeInMinute = findViewById(R.id.tvAgeInMinute)

        btnDatePicker.setOnClickListener {
            clickedDatePicker()
        }
    }

     private fun clickedDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this, "$selectedDayOfMonth/${selectedMonth + 1 }/$selectedYear", Toast.LENGTH_SHORT).show()
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate?.text = selectedDate
                // Date to minute
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val selectedDateInMinute = theDate.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinute = currentDate.time / 60000
                val differentInMinute = currentDateInMinute - selectedDateInMinute
                tvAgeInMinute?.text = differentInMinute.toString()
            },
            year,
            month,
            day
        ).show()
    }
}