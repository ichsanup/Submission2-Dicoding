package com.dicoding.picodiploma.submission2_ichsan

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteUser::class],
    version = 1
)
abstract class UserDbase: RoomDatabase() {

    //for instance directly access
    companion object {
        var INSTANCE : UserDbase? = null

        fun getDatabase(context: Context): UserDbase?{
            if (INSTANCE==null){
                synchronized(UserDbase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, UserDbase::class.java, "user_databse").build()
                }
            }
            return INSTANCE
        }
    }
    abstract fun favoriteUserDaoInterface():FavoriteUserDaoInterface
}