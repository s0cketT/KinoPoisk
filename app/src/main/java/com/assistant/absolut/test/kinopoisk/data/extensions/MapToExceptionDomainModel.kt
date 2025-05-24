package com.tasklist.data.extensions

import com.assistant.absolut.test.kinopoisk.domain.model.ExceptionDomainModel

fun Throwable.mapToExceptionDomainModel(): ExceptionDomainModel = when (this) {

    is java.net.UnknownHostException,
    is java.net.SocketTimeoutException -> ExceptionDomainModel.NoInternet(this)


    is ExceptionDomainModel -> this
    else -> ExceptionDomainModel.Other(this)
}