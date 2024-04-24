package demo

import org.example.demo.BookingService
import org.example.demo.Phone
import org.example.demo.Phones
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import kotlin.test.*

class BookingServiceTest {
    private lateinit var target: BookingService
    private lateinit var db: List<Phone>

    @BeforeEach
    fun setUp() {
        db = Phones.db.map { it.copy() }
        target = BookingService(db)
    }

    @Test
    fun `should book` (){
        target.book("Samsung Galaxy S9", "user1")
        assertEquals(db[0].bookedUser, "user1")
        assertNotNull(db[0].bookedTime)
    }

    @Test
    fun `should return` (){
        assertNull(db[0].bookedTime)
    }
}
