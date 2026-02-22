package com.polarbookshop.catalog_service.web

import com.polarbookshop.catalog_service.domain.BookNotFoundException
import com.polarbookshop.catalog_service.domain.BookService
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import kotlin.test.Test

@WebMvcTest(controllers = [BookController::class])
class BookControllerMvcTests(
    @Autowired private val mockMvc: MockMvc
) {

    @MockitoBean
    lateinit var bookService: BookService

    @Test
    fun `when get book not existing then should return 404`() {
        val isbn = "73737313940"
        given(bookService.viewBookDetails(isbn)).willThrow(BookNotFoundException(isbn))
        mockMvc.get("/books/$isbn")
            .andExpect {
                status { isNotFound() }
            }
    }
}
