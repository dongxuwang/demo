package demo

import java.time.LocalDateTime

/**
 * private field phones default from somewhere:
 * 1. dummy memory data or
 * 2. configured datasource
 */
class BookingService(private val phones: List<MobilePhone> = MobilePhones.datasource) {
    /**
     * book by phone name
     *
     * assumption: pick the first matched from data source, and booked phones are unavailable
     */
    fun book(name: String, user: String): Result<MobilePhone> {
        val phone = phones.filter { it.name == name }.firstOrNull { it.available }
        if (phone == null) {
            return Result.failure(NoSuchElementException(name))
        } else {
            phone.bookedTime = LocalDateTime.now()
            phone.bookedUser = user
            phone.available = false
            return Result.success(phone)
        }
    }

    /**
     * restore by phone id
     *
     * assumption: restored phones are available
     */
    fun restore(id: Int): Result<MobilePhone> {
        val found = phones.firstOrNull { it.id == id }
        if (found == null) {
            return Result.failure(NoSuchElementException("$id"))
        } else {
            found.available = true
            found.restoredTime = LocalDateTime.now()
            return Result.success(found)
        }
    }

    /**
     * book by id
     */
    fun book(id: Int, user: String): Result<MobilePhone> = TODO()

}
