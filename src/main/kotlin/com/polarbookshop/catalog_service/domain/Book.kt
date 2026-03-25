package com.polarbookshop.catalog_service.domain

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import java.time.LocalDateTime

data class Book(

    @Id
    var id: Long? = null,

    @NotBlank(message = "The book ISBN must be defined")
    @Pattern(
        regexp = "^[0-9]{10}|[0-9]{13}\$",
        message = "The ISBN format must be valid"
    )
    val isbn: String,
    @NotBlank(
        message = "The book title must be defined"
    )
    val title: String,
    @NotBlank(
        message = "The book author must be defined"
    )
    val author: String,
    @NotNull(
        message = "The book price must be defined"
    )
    @Positive(
        message = "The book price must be greater than zero"
    )
    val price: Double,
    @Version
    val version: Int = 0,

    @CreatedDate
    val createdDate: LocalDateTime? = null,

    @LastModifiedDate
    val lastModifiedDate: LocalDateTime? = null,

    ) {
    companion object {
        fun of(isbn: String, title: String, author: String, price: Double): Book {
            return Book(
                id = null,
                isbn = isbn,
                title = title,
                author = author,
                price = price,
                version = 0,
                null,
                null
            )
        }
    }
}
