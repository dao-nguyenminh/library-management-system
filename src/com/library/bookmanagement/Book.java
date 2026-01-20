package com.library.bookmanagement;

import java.util.logging.Logger;

public class Book {
    private static final Logger logger = Logger.getLogger(Book.class.getName());

    private String isbn;
    private String title;
    private String author; //1 tác giả duy nhất
    private String publisher;
    private int year;
    private Category category;
    private int totalCopies;
    private int availableCopies;
    private BookStatus status;

    public Book() {
    }

    public Book(String isbn,
                String title,
                String author,
                String publisher,
                int year,
                Category category,
                int totalCopies,
                int availableCopies,
                BookStatus status) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.category = category;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
        this.status = status;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", year=" + year +
                ", category='" + category + '\'' +
                ", totalCopies=" + totalCopies +
                ", availableCopies=" + availableCopies +
                ", status='" + status + '\'' +
                '}';
    }

    /**
     * Check if book is available for borrowing
     */
    public boolean isAvailable() {
        return availableCopies > 0;
    }

    /**
     * Borrow a book (decrease available copies)
     * Returns true if successful, false if no copies available
     */
    public boolean borrowBook() {
        if (availableCopies > 0) {
            availableCopies--;
            updateStatus();
            return true;
        }

        return false;
    }

    /**
     * Return a book (increase available copies)
     */
    public void returnBook() {
        if (availableCopies < totalCopies) {
            availableCopies++;
            updateStatus();
        }
    }

    /**
     * Update status based on available copies
     */
    public void updateStatus() {
        if (availableCopies > 0) {
            status = BookStatus.AVAILABLE;
        } else {
            status = BookStatus.ALL_BORROWED;
        }
    }

    //logging --> Ghi log cho ứng dụng
    public void setTotalCopies(int totalCopies) {
        logger.info("Value of new total copies: " + totalCopies);
        int borrowed = this.totalCopies - this.availableCopies;
        if (totalCopies >= borrowed) {
            int diff = totalCopies - this.totalCopies;
            this.totalCopies = totalCopies;
            this.availableCopies += diff;
            updateStatus();
        } else {
            System.out.println("Cannot reduce total copies below borrowed count!");
            logger.warning("Cannot reduce total copies below borrowed count!");
        }
    }
}
