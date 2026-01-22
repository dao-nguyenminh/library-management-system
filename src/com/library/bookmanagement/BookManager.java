package com.library.bookmanagement;

import java.util.List;

public interface BookManager {
    /**
     * Add new book
     * Returns true if successful, false if ISBN already exists
     */
    boolean addBook(Book book);

    /**
     * Find book by ISBN
     * Return Book if found, null otherwise
     */
    Book findByISBN(String isbn);

    /**
     * Search book by title (contains, case-insensitive)
     */
    List<Book> searchByTitle(String keyword);

    /**
     * Search book by author (contains, case-insensitive)
     */
    List<Book> searchByAuthor(String keyword);

    /**
     * Find book by category
     */
    List<Book> findByCategory(Category cat);

    /**
     * Update book information
     * Can not change ISBN!
     */
    boolean updateBook(String isbn, Book book);

    /**
     * Delete book
     * Only if copies are borrowed
     */
    boolean deleteBook(String isbn);

    /**
     * Get all book as a list
     */
    List<Book> getAllBooks();

    /**
     * Find available books (can be borrowed)
     */
    List<Book> findAvailableBooks();

    /**
     * Get all books sorted by title
     */
    List<Book> sortByTitle();

    /**
     * Get all books sorted by author
     */
    List<Book> sortByAuthor();

    /**
     * Get all books sorted by year (DESC)
     */
    List<Book> sortedByYear();
}
