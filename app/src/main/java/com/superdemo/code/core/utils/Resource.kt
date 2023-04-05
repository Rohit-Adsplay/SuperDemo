package com.qns.qnsmutualfunds.core.utils

import com.superdemo.code.core.utils.Constants

enum class ERROR_TYPE {
    UNKNOWN,
}

sealed class Resource<T>(
    open val data: T? = null,
    open val message: String = Constants.OOPS_SOMETHING_WENT_WRONG,
    open val errorType: ERROR_TYPE = ERROR_TYPE.UNKNOWN
) {

    data class Success<T>(
        override val data: T? = null,
        override val message: String = Constants.RESPONSE_SUCCESS
    ) : Resource<T>(data)

    data class Loading<T>(override val data: T? = null) : Resource<T>(data)

    data class Error<T>(
        override val message: String = Constants.OOPS_SOMETHING_WENT_WRONG,
        override val data: T? = null,
        override val errorType: ERROR_TYPE = ERROR_TYPE.UNKNOWN
    ) : Resource<T>(data)

}