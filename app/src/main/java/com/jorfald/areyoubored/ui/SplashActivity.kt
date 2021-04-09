package com.jorfald.areyoubored.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.jorfald.areyoubored.MainActivity
import com.jorfald.areyoubored.R
import com.jorfald.areyoubored.SHARED_PREF_FILE_NAME
import com.jorfald.areyoubored.SHARED_PREF_KEY_PEOPLE

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPreferences = getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)

        Handler(Looper.getMainLooper()).postDelayed({
            runOnUiThread {
                if (sharedPreferences.contains(SHARED_PREF_KEY_PEOPLE)) {
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    startActivity(Intent(this, WelcomeActivity::class.java))
                }
            }
        }, 500)
    }
}