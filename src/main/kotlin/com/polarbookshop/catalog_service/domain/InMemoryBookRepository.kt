package com.polarbookshop.catalog_service.domain

import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class InMemoryBookRepository(
    private val books: MutableMap<String, Book> = ConcurrentHashMap()
) : BookRepository {
    override fun findAll(): Iterable<Book> = books.values

    override fun findByIsbn(isbn: String): Book? = books[isbn]

    override fun existsByIsbn(isbn: String): Boolean = books.containsKey(isbn)

    override fun save(book: Book): Book = book.also { books[it.isbn] = it }

    override fun deleteByIsbn(isbn: String) { books.remove(isbn) }
}