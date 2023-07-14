package com.example.bitazzademo.service

data class ServiceException(
    var errorCd: String,
    override var message: String?
) : Exception() {
    constructor(errorCd: String) : this(errorCd, null)

    override fun toString(): String = "Error [$errorCd] ~> $message"
}