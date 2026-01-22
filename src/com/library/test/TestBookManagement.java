package com.library.test;

import com.library.bookmanagement.*;

public class TestBookManagement {
    private final BookManager bookManager;

    public TestBookManagement(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    public static void main(String[] args) {
        TestBookManagement testBookManagement = new TestBookManagement(new BookManagerImpl());

        System.out.println("=== LIBRARY MANAGEMENT SYSTEM ===\n");

        //1. Add book
        System.out.println("1. ADDING BOOKS");
        System.out.println("---------------");

        testBookManagement.bookManager.addBook(new Book(
                "ISBN-1234567890",
                "Clean Code",
                "Robert C.Martin",
                "Tri Thu Tre Books",
                2008,
                Category.TECHNOLOGY,
                10,
                10,
                BookStatus.AVAILABLE
        ));

        System.out.println(testBookManagement.bookManager.getAllBooks());
    }
}
