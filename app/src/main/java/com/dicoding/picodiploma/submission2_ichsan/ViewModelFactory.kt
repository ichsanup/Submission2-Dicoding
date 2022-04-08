package com.dicoding.picodiploma.submission2_ichsan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import java.lang.IllegalArgumentException

class ViewModelFactory(private val pref: SettingPreferences) : NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(MainViewModelDarkTheme::class.java)){
            return MainViewModelDarkTheme(pref)as T
        }

        throw IllegalArgumentException("Unknown Viewmodel class" + modelClass.name)
    }

}