package com.superdemo.code.core.networkutils

import com.superdemo.code.core.utils.InternetChecker
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiService: ApiService,
    private val internetChecker: InternetChecker,
) {
    suspend fun getAppSettingPublic() = apiService.appSettingPublic()

}