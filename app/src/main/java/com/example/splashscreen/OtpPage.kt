package com.example.splashscreen

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class OtpPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_page)

        // Find OTP input fields
        val otp1 = findViewById<EditText>(R.id.otp1)
        val otp2 = findViewById<EditText>(R.id.otp2)
        val otp3 = findViewById<EditText>(R.id.otp3)
        val otp4 = findViewById<EditText>(R.id.otp4)
        val btnVerifyOTP = findViewById<Button>(R.id.btnVerifyOTP)

        val otpFields = arrayOf(otp1, otp2, otp3, otp4)

        for (i in otpFields.indices) {
            otpFields[i].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1 && i < otpFields.size - 1) {
                        otpFields[i + 1].requestFocus() // Move to next OTP box
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            otpFields[i].setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL && i > 0 && otpFields[i].text.isEmpty()) {
                    otpFields[i - 1].requestFocus() // Move back when deleting
                }
                false
            })
        }

        findViewById<View>(R.id.btnChangeEmail).setOnClickListener {
            val intent = Intent(this, RegisterPage::class.java)  // Navigate to Login Page
            startActivity(intent)
        }

        // Button click to verify OTP and move to MainPage
        btnVerifyOTP.setOnClickListener {
            val intent = Intent(this, MainPage::class.java)
            startActivity(intent)
            finish() // Close this activity
        }
    }
}
