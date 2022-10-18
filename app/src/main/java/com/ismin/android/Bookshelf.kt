package com.ismin.android

import java.lang.RuntimeException

class Bookshelf {

    private val books: HashMap<String, Book> = HashMap();

    fun addBook(book: Book) {
        books[book.title] = book;
    }

    fun getBook(title: String): Book {
        return books[title] ?: throw RuntimeException("No book with title: $title");
    }

    fun getAllBooks(): List<Book> {
        return books.values.sortedBy { it.title };
    }

    fun getBooksOf(author: String): List<Book> {
        return books.filterValues { it.author.equals(author, ignoreCase = true) }
            .values
            .sortedBy { it.title }
            .toList();
    }

    fun getTotalNumberOfBooks(): Int {
        return books.size;
    }


}
