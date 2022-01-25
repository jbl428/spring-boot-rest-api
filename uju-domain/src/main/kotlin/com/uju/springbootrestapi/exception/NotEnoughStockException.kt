package com.uju.springbootrestapi.exception

import kotlin.RuntimeException

class NotEnoughStockException: RuntimeException {

//    override lateinit var message: String
//    override lateinit var cause: Throwable
//    var enableSuppression: Boolean = false
//    var writableStackTrace: Boolean = false


    constructor() : super()

    constructor(message: String?) : super(message)

    constructor(message: String?, cause: Throwable?) : super(message, cause)

    constructor(cause: Throwable?) : super(cause)

    constructor(message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean) : super(
        message,
        cause,
        enableSuppression,
        writableStackTrace
    )
}