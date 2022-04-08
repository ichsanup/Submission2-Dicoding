package com.dicoding.picodiploma.submission2_ichsan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//class mainviewmodel dibuat untuk menampilkan data2 yang akan diakses pada main activity
class MainViewModel: ViewModel() {

    val listUser = MutableLiveData<ArrayList<User>>()

    fun setSearchUsers(query: String){ /*query sebagai parameter dengan type data string*/
        RetrofitClient.apiInstances
            .getSearchUsers(query)
            .enqueue(object : Callback<UserResponse>{ /*fungsi dari .enqueue yaitu untuk mengeksekusi request secara asynchronus*/
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if(response.isSuccessful){
                        listUser.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("Failure", t.message!!)
                }

            })
    }

    fun getSearchUsers():LiveData<ArrayList<User>>{
        return listUser
    }
}