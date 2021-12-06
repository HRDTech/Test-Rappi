package com.solucioneshr.soft.testrappi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.solucioneshr.soft.testrappi.ui.DisplayActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            startActivity(Intent(this,DisplayActivity::class.java))

            // close this activity
            finish()
        }, 3000)
    }
}