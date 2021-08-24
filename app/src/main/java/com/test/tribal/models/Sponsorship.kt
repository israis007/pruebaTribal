package com.test.tribal.models

import com.google.gson.annotations.SerializedName

data class Sponsorship (
    @SerializedName("impression_urls")      val impressionUrls      : List<String>,
    @SerializedName("tagline")              val tagLine             : String,
    @SerializedName("tagline_url")          val tagLineUrl          : String,
    @SerializedName("sponsor")              val sponsor             : Sponsor
)