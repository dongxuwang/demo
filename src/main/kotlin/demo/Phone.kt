package org.example.demo

import java.time.LocalDateTime

data class Phone(
    val id: Int,
    val name: String,
    var available: Boolean,
    var bookedTime: LocalDateTime?,
    var bookedUser: String?,
    var technology: String? = null,
    var bands2g: String? = null,
    var bands3g: String? = null,
    var bands4g: String? = null,
)

