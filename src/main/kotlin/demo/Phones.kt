package org.example.demo

object Phones {
    val db = listOf(
        Phone(1, "Samsung Galaxy S9", true, null, null),
        Phone(2, "Samsung Galaxy S8", true, null, null),
        Phone(3, "Samsung Galaxy S8", true, null, null),
        Phone(4, "Motorola Nexus 6", true, null, null),
        Phone(5, "Oneplus 9", true, null, null),
        Phone(6, "Apple iPhone 13", true, null, null),
        Phone(7, "Apple iPhone 12", true, null, null),
        Phone(8, "Apple iPhone 11", true, null, null),
        Phone(9, "iPhone X", true, null, null),
        Phone(10, "Nokia 3310", true, null, null),
    )

    init {
        // dummy initialize other information (by fetching from API)
        db.forEach { phone: Phone ->
            phone.also {
                it.technology = "${it.name}-tech"
                it.bands2g = "${it.name}-2g"
                it.bands3g = "${it.name}-3g"
                it.bands4g = "${it.name}-4g"
            }
        }
    }
}
