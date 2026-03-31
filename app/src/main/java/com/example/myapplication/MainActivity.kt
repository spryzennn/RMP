package com.example.myapplication

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sun = findViewById<ImageView>(R.id.sun)
        
        // Load animations
        val sunrise = AnimationUtils.loadAnimation(this, R.anim.sunrise)
        val sunset = AnimationUtils.loadAnimation(this, R.anim.sunset)
        
        // Create a combined animation set
        val dayCycle = AnimationUtils.loadAnimation(this, R.anim.sunrise)
        dayCycle.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                // Start sunset after sunrise completes
                sun.startAnimation(sunset)
            }
        })
        
        // Start the animation
        sun.startAnimation(dayCycle)
    }
}