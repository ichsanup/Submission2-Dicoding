package com.dicoding.picodiploma.submission2_ichsan

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users") //search/users = endpoint
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse> //memasukan type data userrespone

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<DetailUser>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>

}