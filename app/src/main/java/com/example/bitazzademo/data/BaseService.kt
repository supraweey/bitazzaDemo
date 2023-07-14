package com.example.bitazzademo.data

import android.content.Context
import com.example.bitazzademo.domain.APP_COMMON_ERROR
import com.example.bitazzademo.domain.manager.FAIL_TO_DECRYPT_ERROR_CODE
import com.example.bitazzademo.domain.NO_INTERNET_CONNECTION
import com.example.bitazzademo.domain.SOCKET_TIMEOUT
import com.example.bitazzademo.domain.TIMEOUT
import com.example.bitazzademo.domain.UNKNOWN_HOST
import com.example.bitazzademo.domain.manager.handleHttpError
import com.example.bitazzademo.network.Networkable
import com.example.bitazzademo.service.ServiceException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

abstract class BaseService<R, M> constructor(private val networkable: Networkable) : KoinComponent {
    abstract suspend fun callApi(): R
    abstract fun mapper(from: R): M

    private val context: Context by inject()

    fun execute(): Flow<M> = flow { emit(checkInternetConnection()) }
        .map {
            callApi()
        }
        .map {
            mapper(it)
        }
        .catch { error: Throwable ->
            mapError(error)
        }

    private fun checkInternetConnection() {
        if (!networkable.isInternetConnection())
            throw ServiceException(
                NO_INTERNET_CONNECTION,
                NO_INTERNET_CONNECTION
            )
    }

    private fun mapError(e: Throwable) {
        when (e) {
            is TimeoutException ->
                throw ServiceException(TIMEOUT, e.message)

            is SocketTimeoutException ->
                throw ServiceException(SOCKET_TIMEOUT, e.message)

            is UnknownHostException ->
                throw ServiceException(UNKNOWN_HOST, e.message)

            is HttpException ->
                e.handleHttpError()

            is ServiceException ->
                throw ServiceException(e.errorCd, e.message)

            else ->
                e.handleFailToDecryptError()
        }
    }

    private fun Throwable.handleFailToDecryptError() {
        when (message) {
            FAIL_TO_DECRYPT_ERROR_CODE -> throw ServiceException(FAIL_TO_DECRYPT_ERROR_CODE)
            else -> throw ServiceException(APP_COMMON_ERROR, message)
        }
    }
}