package com.example.bitazzademo.service

import com.example.bitazzademo.domain.MISSING_REQUIRE

data class ServiceException(
    var errorCd: String,
    override var message: String?
) : Exception() {
    constructor(errorCd: String) : this(errorCd, null)

    constructor(exception: ServiceException, message: String?) : this(exception.errorCd, message)

    override fun toString(): String = "Error [$errorCd] ~> $message"

    companion object {
        fun missingRequire(message: String?) = ServiceException(MISSING_REQUIRE, message)
    }
}