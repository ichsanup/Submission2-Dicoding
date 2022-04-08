package com.dicoding.picodiploma.submission2_ichsan

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao: FavoriteUserDaoInterface?
    private var userDb: UserDbase?

    init {
        userDb = UserDbase.getDatabase(application)
        userDao = userDb?.favoriteUserDaoInterface()
    }

    fun getFavoriteUser (): LiveData<List<FavoriteUser>>?{
        return userDao?.getFavoriteUser()
    }
}