package io.imrekaszab.eaplayers.core.exception

class HttpException(
    val errorCode: Int,
    val errorMessage: String,
    val responseBody: String? = null
) : NetworkException()
