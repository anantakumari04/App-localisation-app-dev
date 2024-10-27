package com.example.app_local

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.provider.Settings.Global.putString
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var welcomeTextView: TextView
    private val PREF_NAME="app_pref"
    private val LANGUAGE_KEY="language_key"

    private var isEnglish = true // Track current language

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val savedLanguage=getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
            .getString(LANGUAGE_KEY,"en")
        setLocale(savedLanguage!!)

        setContentView(R.layout.activity_main)


        // Set initial welcome message
        val welcomeTextView: TextView = findViewById(R.id.welcomeTextView)
        welcomeTextView.text = getString(R.string.welcome_message)
    }

    // Method to change the language
    fun changeLanguage(view: View) {
        // Toggle language
        val newLanguage = if (Locale.getDefault().language=="en") "es" else "en"
        getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(LANGUAGE_KEY,newLanguage)
            .apply()
        setLocale(newLanguage)
        recreate()
        
    }

    // Method to set the locale
    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)

        // Update the configuration
        resources.updateConfiguration(config, resources.displayMetrics)

        // Refresh the activity
    }
}

