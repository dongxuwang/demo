package example

import java.time.LocalDateTime

interface Service {
    fun book(name: String, user: String): Result<Phone>
    fun restore(name: String): Result<Unit>
}

class BookService(private val datasource: List<Phone> = Phones.datasource) : Service {
    override fun book(name: String, user: String): Result<Phone> {
        val phones = datasource.filter { name == it.device.name }
        if (phones.isEmpty()) {
            return Result.failure(NoSuchElementException("No phone with name $name found"))
        }
        val availablePhone = phones.firstOrNull { it.isAvailable() }
        if (availablePhone == null) {
            return Result.failure(NoSuchElementException("No phone with name $name is available"))
        }
        availablePhone.availability = false
        availablePhone.bookedAt = LocalDateTime.now()
        availablePhone.bookedBy = user
        return Result.success(availablePhone)
    }

    override fun restore(name: String): Result<Unit> {
        val phones = datasource.filter { name == it.device.name }
        if (phones.isEmpty()) {
            return Result.failure(NoSuchElementException("No phone with name $name found"))
        }
        val unavailablePhone = phones.firstOrNull { !it.isAvailable() }
        if (unavailablePhone == null) {
            return Result.failure(NoSuchElementException("All phones with name $name are available"))
        }
        unavailablePhone.availability = true
        return Result.success(Unit)
    }

    // TODO some other useful functions

}
