package com.polarbookshop.catalog_service.domain

import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository
) {

    fun viewBooklist(): Iterable<Book> = bookRepository.findAll()

    fun viewBookDetails(isbn: String): Book =
        bookRepository.findByIsbn(isbn)
            ?: throw BookNotFoundException(isbn)

    fun addBookToCatalog(book: Book): Book =
        book.takeUnless { bookRepository.existsByIsbn(it.isbn) }
            ?.let { bookRepository.save(it) }
            ?: throw BookAlreadyExistsException(book.isbn)

    fun removeBookFromCatalog(isbn: String) =
        bookRepository.deleteByIsbn(isbn)

    fun editBookDetails(isbn: String, book: Book): Book =
        bookRepository.findByIsbn(isbn)
            ?.let {
                bookRepository.save(
                    it.copy(title = book.title, author = book.author, price = book.price)
                )
            } ?: addBookToCatalog(book)


}

class BookNotFoundException(isbn: String) : RuntimeException("The book with ISBN $isbn was not found") {}
class BookAlreadyExistsException(isbn: String) : RuntimeException("A book with ISBN $isbn already exits") {}
