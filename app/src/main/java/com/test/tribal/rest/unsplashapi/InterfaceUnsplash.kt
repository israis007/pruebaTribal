package com.test.tribal.rest.unsplashapi

import com.test.tribal.models.Photos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface InterfaceUnsplash {

    @GET("/photos/random")
    suspend fun getPhotos(@Query("page") page: Int, @Query("client_id") clientID: String): Response<List<Photos>>
}