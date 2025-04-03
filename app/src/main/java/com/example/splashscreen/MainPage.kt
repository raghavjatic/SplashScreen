package com.example.splashscreen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.splashscreen.databinding.ActivityMainBinding
import com.example.splashscreen.databinding.ActivityMainPageBinding

class MainPage : AppCompatActivity() {

    private lateinit var binding: ActivityMainPageBinding  //for fragments

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding= ActivityMainPageBinding.inflate(layoutInflater)


        enableEdgeToEdge()
        setContentView(binding.root)
        replaceFragment(home())

        binding.bottomNavigationView.setOnItemSelectedListener {


            when(it.itemId){

                R.id.home -> replaceFragment(home())
                R.id.add_space -> replaceFragment(add_space())
                R.id.profile -> replaceFragment(profile())

            }
            true
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
    }

private fun replaceFragment(fragment : Fragment){

    val fragmentManager = supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.frameLayout,fragment)
    fragmentTransaction.commit()
}

}