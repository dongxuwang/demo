package demo

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.*

class BookingServiceTest {
    private lateinit var target: BookingService
    private lateinit var datasource: List<MobilePhone>

    @BeforeEach
    fun setUp() {
        datasource = MobilePhones.datasource.map { it.copy() }
        target = BookingService(datasource)
    }

    @Test
    fun `should book by name`() {
        val actual = target.book("Samsung Galaxy S9", "user1")
        assertTrue(actual.isSuccess)
        assertEquals(actual.getOrThrow().bookedUser, "user1")
        assertNotNull(actual.getOrThrow().bookedTime)
    }

    @Test
    fun `should fail when book by unknown phone name`() {
        val actual = target.book("unknown name", "user1")
        assertTrue(actual.isFailure)
        val ex = assertThrows<NoSuchElementException> { actual.getOrThrow() }
        assertEquals(ex.message, "unknown name")
    }

    @Test
    fun `should restore by id`() {
        datasource[0].available = false
        val actual = target.restore(1)
        assertTrue(actual.isSuccess)
        assertTrue(actual.getOrThrow().available)
    }

    @Test
    fun `should fail when restore by some unknown phone id`() {
        val actual = target.restore(Int.MIN_VALUE)
        assertTrue(actual.isFailure)
        val ex = assertThrows<NoSuchElementException> { actual.getOrThrow() }
        assertEquals(ex.message, "${Int.MIN_VALUE}")
    }
}
