package com.test.tribal.rest.punkapi

import com.test.tribal.models.Photos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface InterfaceUnsplash {

    @GET("/photos/random")
    suspend fun getPhotos(@Query("page") page: Int, @Query("client_id") clientID: String): Response<List<Photos>>
}