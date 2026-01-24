package com.library.utils;

import java.time.LocalDate;
import java.util.regex.Pattern;

public final class InputValidator {
    //Email regex: basic validation
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^0\\d{9}$");

    private static final Pattern MEMBER_ID_PATTERN =
            Pattern.compile("^MEM-\\d{5}$");

    private static final Pattern ISBN_PATTERN =
            Pattern.compile("^ISBN-\\d{10}$");

    //Utils Class --> Private Constructor
    private InputValidator() {
    }

    /**
     * Validate email format
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    /**
     * Validate phone format
     */
    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }

        return PHONE_PATTERN.matcher(phone.trim()).matches();
    }

    /**
     * Validate ISBN format
     */
    public static boolean isValidISBN(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            return false;
        }

        return ISBN_PATTERN.matcher(isbn.trim()).matches();
    }

    /**
     * Validate Member ID format
     */
    public static boolean isValidMemberId(String memberId) {
        if (memberId == null || memberId.trim().isEmpty()) {
            return false;
        }

        return MEMBER_ID_PATTERN.matcher(memberId.trim()).matches();
    }

    /**
     * Validate year format
     */
    public static boolean isValidYear(int year) {
        int currentYear = LocalDate.now().getYear();
        return year >= 1900 && year <= currentYear;
    }

    /**
     * Validate number format
     */
    public static boolean isPositiveInteger(int num) {
        return num > 0;
    }

    /**
     * Validate string format
     */
    public static boolean isNonEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }
}
