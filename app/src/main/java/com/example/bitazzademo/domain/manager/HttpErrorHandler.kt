package com.example.bitazzademo.domain.manager

import com.example.bitazzademo.service.ServiceException
import retrofit2.HttpException

private const val HTTP_ERROR_CODE_400 = 400
private const val HTTP_ERROR_CODE_404 = 404
private const val HTTP_ERROR_CODE_500 = 500
private const val HTTP_ERROR_CODE_502 = 502
private const val HTTP_ERROR_CODE_503 = 503
private const val ERROR_CODE_BZA001 = "BZA001"
private const val ERROR_CODE_BZA002 = "BZA002"
private const val ERROR_CODE_BZA003 = "BZA003"
private const val ERROR_CODE_BZA004 = "BZA004"
private const val ERROR_CODE_BZA005 = "BZA005"
private const val ERROR_CODE_BZA999 = "BZA999"
const val FAIL_TO_DECRYPT_ERROR_CODE = "BZA998"

fun HttpException.handleHttpError() {
    when (code()) {
        HTTP_ERROR_CODE_400 -> throw ServiceException(ERROR_CODE_BZA001)
        HTTP_ERROR_CODE_404 -> throw ServiceException(ERROR_CODE_BZA002)
        HTTP_ERROR_CODE_500 -> throw ServiceException(ERROR_CODE_BZA003)
        HTTP_ERROR_CODE_502 -> throw ServiceException(ERROR_CODE_BZA004)
        HTTP_ERROR_CODE_503 -> throw ServiceException(ERROR_CODE_BZA005)
        else -> throw ServiceException(ERROR_CODE_BZA999)
    }
}