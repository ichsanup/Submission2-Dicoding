package com.dicoding.picodiploma.submission2_ichsan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModelDarkTheme(private val pref: SettingPreferences): ViewModel() {

    fun getThemeSetting(): LiveData<Boolean>{
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModelActive: Boolean){
        viewModelScope.launch {
            pref.saveThemeSetting((isDarkModelActive))
        }
    }
}