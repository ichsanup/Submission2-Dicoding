package com.dicoding.picodiploma.submission2_ichsan

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore



private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SplashScreenActivity : Activity() {

    private val SplashScreen: Long = 1200


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val pref = SettingPreferences.getInstance(dataStore)


        Handler().postDelayed({
            startActivity(Intent(this,
                MainActivity::class.java)) //perpindahan dari this to mainactivity
            finish()

        }, SplashScreen)

    }

}