package example

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.*

class BookServiceTest {
    private lateinit var target: Service
    private lateinit var datasource: List<Phone>

    @BeforeEach
    fun setUp() {
        datasource = Phones.datasource.map { it.copy() }
        target = BookService(datasource)
    }

    @Test
    fun `should book phone by name`() {
        val actual = target.book("Samsung Galaxy S9", "user1")

        assertTrue(actual.isSuccess)
        val actualPhone = actual.getOrThrow()
        assertEquals(actualPhone.bookedBy, "user1")
        assertNotNull(actualPhone.bookedAt)
        assertFalse(actualPhone.availability)
    }

    @Test
    fun `should fail when book by unknown phone name`() {
        val actual = target.book("unknown name", "user1")
        assertTrue(actual.isFailure)
        val ex = assertThrows<NoSuchElementException> { actual.getOrThrow() }
        assertEquals(ex.message, "No phone with name unknown name found")
    }

    @Test
    fun `should fail when book by unavailable phone name`() {
        datasource.forEach { it.availability = false }

        val actual = target.book("Samsung Galaxy S9", "user1")
        assertTrue(actual.isFailure)
        val ex = assertThrows<NoSuchElementException> { actual.getOrThrow() }
        assertEquals(ex.message, "No phone with name Samsung Galaxy S9 is available")
    }

    @Test
    fun `should restore by name`() {
        datasource.forEach { it.availability = false }

        val actual = target.restore("Samsung Galaxy S9")
        assertTrue(actual.isSuccess)
    }

    @Test
    fun `should fail when restore by unknown phone name`() {
        val actual = target.restore("unknown name")
        assertTrue(actual.isFailure)
        val ex = assertThrows<NoSuchElementException> { actual.getOrThrow() }
        assertEquals(ex.message, "No phone with name unknown name found")
    }

    @Test
    fun `should fail when restore to the available phone`() {
        val actual = target.restore("Samsung Galaxy S9")
        assertTrue(actual.isFailure)
        val ex = assertThrows<NoSuchElementException> { actual.getOrThrow() }
        assertEquals(ex.message, "All phones with name Samsung Galaxy S9 are available")
    }
}
