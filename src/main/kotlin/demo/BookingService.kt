package org.example.demo

import java.time.LocalDateTime

class BookingService(val phones: List<Phone>) {
    /**
     *
     */
    fun book(name: String, user: String): Boolean {
        val phone = phones.filter { it.name == name }.firstOrNull { it.available }
        if (phone != null) {
            phone.bookedTime = LocalDateTime.now()
            phone.bookedUser = user
            phone.available = false
            return true
        }
        return false
    }

    fun restore(id: Int) {
        phones.first { it.id == id }.also {
            it.available = true
        }
    }

}
