package com.dicoding.picodiploma.submission2_ichsan

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteUserDaoInterface {

    @Insert
    suspend fun addToFavorite(favoriteUser: FavoriteUser)

    //menampilkan list dari favorite user
    @Query("SELECT*FROM favorite_user")
    fun getFavoriteUser(): LiveData<List<FavoriteUser>>

    @Query("SELECT count(*) FROM favorite_user WHERE favorite_user.id = :id")
    suspend fun checkUser(id:Int): Int

    //menghapus favorite user
    @Query("DELETE FROM favorite_user WHERE favorite_user.id = :id")
    suspend fun removeUser(id: Int):Int

}