package com.polarbookshop.catalog_service.domain

import com.polarbookshop.catalog_service.config.DataConfig
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.data.jdbc.test.autoconfigure.DataJdbcTest
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase
import org.springframework.context.annotation.Import
import org.springframework.data.jdbc.core.JdbcAggregateTemplate
import org.springframework.test.context.ActiveProfiles
import kotlin.test.Test

@DataJdbcTest
@Import(DataConfig::class)
@AutoConfigureTestDatabase(
    replace = AutoConfigureTestDatabase.Replace.NONE
)
@ActiveProfiles("integration")
class BookRepositoryJdbcTests(
    @Autowired private val bookRepository: BookRepository,
    @Autowired private val jdbcAggregateTemplate: JdbcAggregateTemplate
) {

    @Test
    fun findBookByIsbnWhenExisting() {
        val bookIsbn = "1234567"
        val book = Book.of(
            isbn = bookIsbn,
            title = "Title",
            author = "Author",
            publisher = "tester",
            price = 12.90
        )
        jdbcAggregateTemplate.insert(book)
        bookRepository.findByIsbn(bookIsbn)
            .let { book ->
                assertThat(book).isNotNull
                assertThat(book?. isbn).isEqualTo(bookIsbn)
            }
    }

}
