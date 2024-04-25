package demo

object MobilePhones {
    val datasource = listOf(
        MobilePhone(1, "Samsung Galaxy S9", true, null, null, null),
        MobilePhone(2, "Samsung Galaxy S8", true, null, null, null),
        MobilePhone(3, "Samsung Galaxy S8", true, null, null, null),
        MobilePhone(4, "Motorola Nexus 6", true, null, null, null),
        MobilePhone(5, "Oneplus 9", true, null, null, null),
        MobilePhone(6, "Apple iPhone 13", true, null, null, null),
        MobilePhone(7, "Apple iPhone 12", true, null, null, null),
        MobilePhone(8, "Apple iPhone 11", true, null, null, null),
        MobilePhone(9, "iPhone X", true, null, null, null),
        MobilePhone(10, "Nokia 3310", true, null, null, null),
    )

    init {
        // dummy initialize other information (by fetching from API)
        datasource.forEach { phone: MobilePhone ->
            phone.also {
                it.technology = "${it.name}-tech"
                it.bands2g = "${it.name}-2g"
                it.bands3g = "${it.name}-3g"
                it.bands4g = "${it.name}-4g"
            }
        }
    }
}
