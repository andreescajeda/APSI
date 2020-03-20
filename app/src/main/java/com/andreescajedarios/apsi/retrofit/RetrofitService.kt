package com.andreescajedarios.apsi.retrofit

import com.andreescajedarios.apsi.data_classes.PlpResponse
import retrofit2.Response
import retrofit2.http.*

interface RetrofitService {

    @GET("plp")
    suspend fun plp(
        @Query("force-plp") forcePlp: Boolean,
        @Query("search-string") searchString: String,
        @Query("page-number") pageNumber: String,
        @Query("number-of-items-per-page") numberOfItemsPerPage: String) : Response<PlpResponse>
}