package com.dicoding.picodiploma.submission2_ichsan

data class DetailUser(
    val login : String,
    val name : String,
    val id : Int,
    val avatar_url : String,
    val followers_url : String,
    val following_url : String,
    val followers : Int,
    val following : Int,
    val public_repos : Int,
    val company : String,
    val html_url : String,
    val location : String

)
