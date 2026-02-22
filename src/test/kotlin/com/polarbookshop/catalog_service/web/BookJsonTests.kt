package com.polarbookshop.catalog_service.web

import com.polarbookshop.catalog_service.domain.Book
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester
import kotlin.test.Test

@JsonTest
class BookJsonTests(
    @Autowired private val json: JacksonTester<Book>
) {

    @Test
    fun `when serialize book then json is valid`() {
        val book = Book(isbn = "1234567890", title = "Title", author = "Author", price = 100.0)
        val jsonContent = json.write(book)
        assertThat(jsonContent).apply {
            extractingJsonPathValue("@.isbn").isEqualTo(book.isbn)
            extractingJsonPathValue("@.title").isEqualTo(book.title)
            extractingJsonPathValue("@.author").isEqualTo(book.author)
            extractingJsonPathValue("@.price").isEqualTo(book.price)
        }
    }

    @Test
    fun `when deserialize book then json is valid`() {
        val content = """
            {
            "isbn": "1234567890",
            "title": "Title",
            "author": "Author",
            "price": 100.0
            }
        """
        assertThat(json.parse(content))
            .usingRecursiveComparison()
            .isEqualTo(Book(isbn = "1234567890", title = "Title", author = "Author", price = 100.0))

    }
}