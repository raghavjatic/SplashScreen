package com.example.splashscreen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// Define login request data model
data class LoginRequest(val email: String, val password: String)

// Define login response model
data class LoginResponse(val success: Boolean, val token: String?)

// Retrofit interface
interface ApiService {
    @POST("/signin")  // Node.js backend endpoint
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}


class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_page)

        // Find the login button
        val loginButton = findViewById<Button>(R.id.btnLogin)

        // Set a click listener to navigate to MainPage.kt
        loginButton.setOnClickListener {
            val intent = Intent(this, MainPage::class.java)
            startActivity(intent)
            finish() // Closes the login page so user can't go back to it
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
