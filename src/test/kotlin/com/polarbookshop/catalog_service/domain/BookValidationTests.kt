package com.polarbookshop.catalog_service.domain

import jakarta.validation.Validation
import jakarta.validation.Validator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import kotlin.test.Test

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookValidationTests {

    private lateinit var validator: Validator

    @BeforeAll
    fun init() {
        validator = Validation.buildDefaultValidatorFactory().validator
    }

    @Test
    fun `when all fields correct then validation succeeds`() {
        val book = Book(isbn = "1234567890", title = "Title", author = "Author", price = 9.90)
        val violations = validator.validate(book)
        assertThat(violations).isEmpty()
    }

    @Test
    fun `when all fields incorrect then validation fails`() {
        val book = Book(isbn = "a1234567890", title = "Title", author = "Author", price = 9.90)
        val violations = validator.validate(book)
        assertThat(violations).hasSize(1)
        assertThat(violations.first().message).isEqualTo("The ISBN format must be valid")
    }
}