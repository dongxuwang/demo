package example

import java.time.LocalDateTime

data class Device(
    val name: String,
    val brand: String,
    var technology: String? = null,
    var _2gBand: String? = null,
    var _3gBand: String? = null,
    var _4gBand: String? = null,
)

data class Phone(
    val id: Int,
    val device: Device,
    var availability: Boolean = true,
    var bookedAt: LocalDateTime? = null,
    var bookedBy: String? = null,
)

fun Phone.isAvailable() = availability

object Phones {
    val datasource = listOf(
        Phone(1, Device("Samsung Galaxy S9", "Samsung")),
        Phone(2, Device("Samsung Galaxy S8", "Samsung")),
        Phone(3, Device("Samsung Galaxy S8", "Samsung")),
        Phone(4, Device("Motorola Nexus 6", "Motorola")),
        Phone(5, Device("Oneplus 9", "Oneplus")),
        Phone(6, Device("Apple iPhone 13", "Apple")),
        Phone(7, Device("Apple iPhone 12", "Apple")),
        Phone(8, Device("Apple iPhone 11", "Apple")),
        Phone(9, Device("iPhone X", "Apple")),
        Phone(10, Device("Nokia 3310", "Nokia")),
    )

    init {
        datasource.forEach { }
    }

    fun findByDeviceName(name: String): List<Phone> = datasource.filter { name == it.device.name }
}
