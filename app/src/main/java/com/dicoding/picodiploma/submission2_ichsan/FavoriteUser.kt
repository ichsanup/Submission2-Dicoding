package com.dicoding.picodiploma.submission2_ichsan

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//tambahkan anotation entity and primarykey
@Entity(tableName = "favorite_user")
data class FavoriteUser(
    val login : String,
    @PrimaryKey
    val id:Int,
    val avatar_url:String
): Serializable
