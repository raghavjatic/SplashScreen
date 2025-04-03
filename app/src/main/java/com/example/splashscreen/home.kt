package com.example.splashscreen

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import java.util.Calendar

class home : Fragment() {

    private lateinit var extraMenu: LinearLayout
    private lateinit var findSpaceButton: Button
    private lateinit var lengthInput: EditText
    private lateinit var widthInput: EditText
    private lateinit var fromTime: EditText
    private lateinit var untilTime: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize UI elements
        extraMenu = view.findViewById(R.id.extra_menu)
        findSpaceButton = view.findViewById(R.id.find_space_button)
        lengthInput = view.findViewById(R.id.editTextLength)
        widthInput = view.findViewById(R.id.editTextWidth)
        fromTime = view.findViewById(R.id.editTextFrom)
        untilTime = view.findViewById(R.id.editTextUntil)

        val carOption = view.findViewById<CardView>(R.id.car_option)
        val bikeOption = view.findViewById<CardView>(R.id.bike_option)
        val busOption = view.findViewById<CardView>(R.id.bus_option)

        // Click listener to toggle extra menu and show fields
        val vehicleClickListener = View.OnClickListener {
            if (extraMenu.visibility == View.GONE) {
                extraMenu.visibility = View.VISIBLE
            } else {
                extraMenu.visibility = View.GONE
            }
        }

        carOption.setOnClickListener(vehicleClickListener)
        bikeOption.setOnClickListener(vehicleClickListener)
        busOption.setOnClickListener(vehicleClickListener)

        // Set TimePicker for fromTime and untilTime
        fromTime.setOnClickListener {
            showTimePickerDialog(fromTime)
        }

        untilTime.setOnClickListener {
            showTimePickerDialog(untilTime)
        }

        return view
    }

    private fun showTimePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { _, selectedHour, selectedMinute ->
                val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                editText.setText(formattedTime)
            },
            hour,
            minute,
            true
        )
        timePickerDialog.show()
    }
}
