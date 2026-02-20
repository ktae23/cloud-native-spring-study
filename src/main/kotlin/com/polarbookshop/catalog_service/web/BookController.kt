package com.polarbookshop.catalog_service.web

import com.polarbookshop.catalog_service.domain.Book
import com.polarbookshop.catalog_service.domain.BookService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookController(
    private val bookService: BookService
) {

    @GetMapping
    fun get(): Iterable<Book> = bookService.viewBooklist()

    @GetMapping("{isbn}")
    fun getByIsbn(@PathVariable isbn: String): Book = bookService.viewBookDetails(isbn = isbn)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun post(@RequestBody book: Book): Book = bookService.addBookToCatalog(book = book)

    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable isbn: String): Unit = bookService.removeBookFromCatalog(isbn = isbn)

    @PutMapping("{isbn}")
    fun put(@PathVariable isbn: String, @RequestBody book: Book): Book = bookService.editBookDetails(isbn = isbn, book = book)

}
