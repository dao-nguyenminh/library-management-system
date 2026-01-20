package com.library.bookmanagement;

public enum Category {
    FICTION("Tiểu thuyết, văn học"),
    SCIENCE("Khoa học tự nhiên"),
    HISTORY("Lịch sử, nhân văn"),
    TECHNOLOGY("Công nghệ, lập trình");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
