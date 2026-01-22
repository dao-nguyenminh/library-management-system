package com.library.bookmanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class BookManagerImpl implements BookManager {
    private static final Logger logger = Logger.getLogger(BookManagerImpl.class.getName());
    //Hash Map: ISBN -> Book
    private final HashMap<String, Book> books;

    public BookManagerImpl() {
        this.books = new HashMap<>();
    }


    @Override
    public boolean addBook(Book book) {
        if (books.containsKey(book.getIsbn())) {
            logger.severe("Error: ISBN already exists!");
            return false;
        }

        books.put(book.getIsbn(), book);
        logger.info("Book add successfully");
        return false;
    }

    @Override
    public Book findByISBN(String isbn) {
        return books.get(isbn); //O(1)
    }

    @Override
    public List<Book> searchByTitle(String keyword) {
        List<Book> results = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase().contains(lowerKeyword)) {
                results.add(book);
            }
        }

        return results;
    }

    @Override
    public List<Book> searchByAuthor(String keyword) {
        List<Book> results = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (Book book : books.values()) {
            if (book.getAuthor().toLowerCase().contains(lowerKeyword)) {
                results.add(book);
            }
        }
        return results;
    }

    @Override
    public List<Book> findByCategory(Category cat) {
        List<Book> results = new ArrayList<>();

        for (Book book : books.values()) {
            if (book.getCategory() == cat) {
                results.add(book);
            }
        }
        return results;
    }

    @Override
    public boolean updateBook(String isbn, Book book) {
        if (!books.containsKey(isbn)) {
            logger.severe("Error: Book not found");
            return false;
        }

        Book existingBook = books.get(isbn);

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPublisher(book.getPublisher());
        existingBook.setYear(book.getYear());
        existingBook.setCategory(book.getCategory());
        existingBook.setTotalCopies(book.getTotalCopies());

        logger.info(String.format("Update book by ISBN = %s successfully", isbn));
        return true;
    }

    @Override
    public boolean deleteBook(String isbn) {
        Book book = books.get(isbn);

        if (book == null) {
            logger.severe("Error: Book not found");
            return false;
        }

        //availableCopies < totalCopies ???? Xoá được ko????
        if (book.getAvailableCopies() < book.getTotalCopies()) {
            logger.severe("Error: Cannot delete book with borrowed copies");
            return false;
        }

        books.remove(isbn);
        logger.info("Book deleted successfully!");
        return true;
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    @Override
    public List<Book> findAvailableBooks() {
        List<Book> results = new ArrayList<>();

        for (Book book : books.values()) {
            if (book.isAvailable()) {
                results.add(book);
            }
        }

        return results;
    }

    @Override
    public List<Book> sortByTitle() {
        List<Book> sortedBooks = getAllBooks();

        sortedBooks.sort((o1, o2) -> o1.getTitle().compareToIgnoreCase(o2.getTitle()));

        return sortedBooks;
    }

    @Override
    public List<Book> sortByAuthor() {
        List<Book> sortedBooks = getAllBooks();

        sortedBooks.sort((o1, o2) -> o1.getAuthor().compareToIgnoreCase(o2.getAuthor()));

        return sortedBooks;
    }

    @Override
    public List<Book> sortedByYear() {
        List<Book> sortedBooks = getAllBooks();

        sortedBooks.sort((o1, o2) -> o2.getYear() - o1.getYear());
        return List.of();
    }
}
