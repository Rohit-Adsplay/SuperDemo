package com.superdemo.code.core.networkutils

import retrofit2.Response
import retrofit2.http.*

interface ApiService {


    @Headers("Accept: application/json")
    @POST("api/app_setting_public")
    suspend fun appSettingPublic(
    ): Response<String> //model class here

}