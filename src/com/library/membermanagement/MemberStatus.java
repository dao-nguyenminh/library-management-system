package com.library.membermanagement;

public enum MemberStatus {
    ACTIVE("Hoạt động"),
    SUSPENDED("Bị khoá");

    private final String displayName;

    MemberStatus(String displayName) {
        this.displayName = displayName;
    }

    //Getter
    public String getDisplayName() {
        return displayName;
    }

    //Useful Helper
    public boolean isActive() {
        return this == ACTIVE;
    }
}
