package com.vigneshtheagarajan.mytest.network

import com.vigneshtheagarajan.mytest.model.MainActivityModel
import retrofit2.http.GET

interface ApiCommonService {

    @GET("5b700cff2e00005c009365cf")
    suspend fun gettest(): MainActivityModel
}