package com.example.splashscreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

// API Request Model
data class RegisterRequest(
    val name: String,
    val email: String,
    val phone: String,
    val password: String
)

// API Response Model
data class RegisterResponse(
    val success: Boolean,
    val message: String,
    val data :Any?,
    val error :Any?,
)

// Retrofit API Service
interface ApiService {
    @POST("auth/signup")  // Adjust the endpoint to match your backend
    fun registerUser(@Body request: RegisterRequest): Call<RegisterResponse>
}

class RegisterPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)

        findViewById<View>(R.id.btnAlreadyRegistered).setOnClickListener {
            startActivity(Intent(this, LoginPage::class.java))
        }

        findViewById<View>(R.id.btnRegister).setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val name = findViewById<EditText>(R.id.name).text.toString().trim()
        val email = findViewById<EditText>(R.id.email).text.toString().trim()
        val phone = findViewById<EditText>(R.id.Phone).text.toString().trim()
        val password = findViewById<EditText>(R.id.Password).text.toString().trim()

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val request = RegisterRequest(name, email, phone, password)

        RetrofitInstance.apiService.registerUser(request).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful && response.body()?.success == true) {
                    Toast.makeText(applicationContext, "Registration Successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@RegisterPage, OtpPage::class.java))
                } else {
                    Toast.makeText(applicationContext, response.body()?.message ?: "Registration Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
