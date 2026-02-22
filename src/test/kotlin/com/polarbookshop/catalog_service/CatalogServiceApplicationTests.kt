package com.polarbookshop.catalog_service

import com.polarbookshop.catalog_service.domain.Book
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class CatalogServiceApplicationTests(
    @Autowired private val webTestClient: WebTestClient
) {

    @Test
    fun `when post request then book created`() {
        val expectedBook = Book("1231231231", "Title", "Author", 9.90)
        webTestClient.post()
            .uri("/books")
            .bodyValue(expectedBook)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isCreated
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody(Book::class.java)
            .value { actualBook ->
                assertThat(actualBook).isNotNull
                assertThat(actualBook!!.isbn).isEqualTo(expectedBook.isbn)
            }
    }
}
