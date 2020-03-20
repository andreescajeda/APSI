package com.andreescajedarios.apsi.retrofit

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitFactory {

    private const val BASE_URL = "https://shoppapp.liverpool.com.mx/appclienteservices/services/v3/"

    fun makeRetrofitService() : RetrofitService {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .build()
            .create(RetrofitService::class.java)
    }
}