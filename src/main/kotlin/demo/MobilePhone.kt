package demo

import java.time.LocalDateTime

data class MobilePhone(
    val id: Int,
    var name: String,
    var available: Boolean,
    var bookedTime: LocalDateTime?,
    var restoredTime: LocalDateTime?,
    var bookedUser: String?,
    var technology: String? = null,
    var bands2g: String? = null,
    var bands3g: String? = null,
    var bands4g: String? = null,
)

