package com.assistant.absolut.test.kinopoisk.presentation

import com.assistant.absolut.test.kinopoisk.R
import com.assistant.absolut.test.kinopoisk.domain.model.ExceptionDomainModel


fun ExceptionDomainModel.parseToString(): Int {
    return when (this) {
        is ExceptionDomainModel.NoInternet -> R.string.error_no_internet
        is ExceptionDomainModel.Other -> R.string.error_api
    }
}