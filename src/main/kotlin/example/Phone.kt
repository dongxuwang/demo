package example

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.LocalDateTime

data class Device(
    val name: String,
    val brand: String,
    var technology: String? = null,
    var _2gband: String? = null,
    var _3gband: String? = null,
    var _4gband: String? = null,
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
        val mapper = jacksonObjectMapper()
        // fetching dummy data from mocked fonoapi
        runCatching {
            val client = HttpClient.newHttpClient()
            val request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.jsonserver.io/getdevice"))
                .header("Accept", "application/json")
                .header("X-Jsio-Token", "cd8cbd92d0174d253721ed85655fc3d8")
                .GET()
                .build()

            val response = client.send(request, HttpResponse.BodyHandlers.ofString())
            val device = mapper.readValue<List<Device>>(response.body())[0]

            datasource.forEach { phone ->
                phone.device.also {
                    it.technology = device.technology
                    it._2gband = device._2gband
                    it._3gband = device._3gband
                    it._4gband = device._4gband
                }
            }
        }

    }

    // For testing talking to the mocked api
    @JvmStatic
    fun main(args: Array<String>) {
        datasource.forEach { println(it) }
    }
}
