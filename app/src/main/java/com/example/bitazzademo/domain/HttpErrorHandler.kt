package com.example.bitazzademo.domain

import com.example.bitazzademo.service.ServiceException
import retrofit2.HttpException

private const val HTTP_ERROR_CODE_400 = 400
private const val HTTP_ERROR_CODE_404 = 404
private const val HTTP_ERROR_CODE_500 = 500
private const val HTTP_ERROR_CODE_502 = 502
private const val HTTP_ERROR_CODE_503 = 503
private const val ERROR_CODE_HCA001 = "HCA001"
private const val ERROR_CODE_HCA002 = "HCA002"
private const val ERROR_CODE_HCA003 = "HCA003"
private const val ERROR_CODE_HCA004 = "HCA004"
private const val ERROR_CODE_HCA005 = "HCA005"
private const val ERROR_CODE_HCA999 = "HCA999"
const val FAIL_TO_DECRYPT_ERROR_CODE = "HCA998"

fun HttpException.handleHttpError() {
    when (code()) {
        HTTP_ERROR_CODE_400 -> throw ServiceException(ERROR_CODE_HCA001)
        HTTP_ERROR_CODE_404 -> throw ServiceException(ERROR_CODE_HCA002)
        HTTP_ERROR_CODE_500 -> throw ServiceException(ERROR_CODE_HCA003)
        HTTP_ERROR_CODE_502 -> throw ServiceException(ERROR_CODE_HCA004)
        HTTP_ERROR_CODE_503 -> throw ServiceException(ERROR_CODE_HCA005)
        else -> throw ServiceException(ERROR_CODE_HCA999)
    }
}