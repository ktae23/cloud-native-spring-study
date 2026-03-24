package com.polarbookshop.catalog_service.demo

import com.polarbookshop.catalog_service.domain.Book
import com.polarbookshop.catalog_service.domain.BookRepository
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Profile
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
@Profile("testdata")
class BookDataLoader(
    private val bookRepository: BookRepository,
) {

    @EventListener(ApplicationReadyEvent::class)
    fun loadBookTestData(): Unit {
        bookRepository.save(
            Book(
                isbn = "1234567891",
                title = "Northern Lights",
                author = "Lyra Silver star",
                price = 9.90
            )
        )
        bookRepository.save(
            Book(
                isbn = "1234567892",
                title = "Polar Journey",
                author = "Iorek Polarson",
                price = 12.90
            )
        )

    }
}