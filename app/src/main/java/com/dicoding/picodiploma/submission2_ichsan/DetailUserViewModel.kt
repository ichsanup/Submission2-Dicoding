package com.dicoding.picodiploma.submission2_ichsan

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(application: Application) : AndroidViewModel(application) {
    val user = MutableLiveData<DetailUser>()

    private val userDao: FavoriteUserDaoInterface?
    private var userDb: UserDbase?

    init {
        userDb = UserDbase.getDatabase(application)
        userDao = userDb?.favoriteUserDaoInterface()
    }
    fun setUserDetail(username: String?) {
        if (username != null) {
            RetrofitClient.apiInstances
                .getUserDetail(username)
                .enqueue(object : Callback<DetailUser> {
                    override fun onResponse(
                        call: Call<DetailUser>,
                        response: Response<DetailUser>,
                    ) {
                        if (response.isSuccessful) {
                            user.postValue(response.body())
                        }
                    }

                    override fun onFailure(call: Call<DetailUser>, t: Throwable) {
                        Log.d("Failure", t.message!!)
                    }

                })
        }
    }

    fun getUserDetail(): LiveData<DetailUser> {
        return user
    }

    fun addToFavorite(username: String, id: Int, avatarUrl:String){

        CoroutineScope(Dispatchers.IO).launch {
            var user = FavoriteUser(
                username,
                id,
                avatarUrl
            )
            userDao?.addToFavorite(user)
        }
    }
    //check user
    suspend fun CheckUser (id:Int) =userDao?.checkUser(id)

    //removeuser
    fun removeFromFavorite (id:Int){
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.removeUser(id)
        }
    }
}