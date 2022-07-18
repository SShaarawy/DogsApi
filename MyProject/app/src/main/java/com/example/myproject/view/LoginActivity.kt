package com.example.myproject.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.Settings
import com.example.myproject.databinding.ActivityLoginScreenBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

       GlobalScope.launch {
            for(i in 1..5){
                delay(1000)
                println("time $i")
                binding.progressBar.progress = i
            }
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


       /*var counter = 1
       object : CountDownTimer(5000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.progressBar.progress = counter
                println("time: ${(millisUntilFinished / 1000).toInt()}, counter $counter")
                counter++
            }

            override fun onFinish() {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.start()*/

    }
}