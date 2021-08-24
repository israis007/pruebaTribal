package com.test.tribal.models

import com.google.gson.annotations.SerializedName

data class Urls (
    @SerializedName("raw")      val RAW         : String,
    @SerializedName("full")     val FULL        : String,
    @SerializedName("regular")  val REGULAR     : String,
    @SerializedName("small")    val SMALL       : String,
    @SerializedName("thumb")    val THUMB       : String
)