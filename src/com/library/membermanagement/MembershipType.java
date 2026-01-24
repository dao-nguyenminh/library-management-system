package com.library.membermanagement;

public enum MembershipType {
    STANDARD(3, 14, "Tiêu Chuẩn"),
    PREMIUM(5, 21, "Cao Cấp"),
    VIP(10, 30, "VIP");

    private final int maxBooks;
    private final int borrowDays;
    private final String displayName;

    MembershipType(int maxBooks, int borrowDays, String displayName) {
        this.maxBooks = maxBooks;
        this.borrowDays = borrowDays;
        this.displayName = displayName;
    }

    //Getter
    public int getMaxBooks() {
        return maxBooks;
    }

    public int getBorrowDays() {
        return borrowDays;
    }

    public String getDisplayName() {
        return displayName;
    }

    //Business Logic
    public boolean canUpgradeTo(MembershipType newType) {
        return newType.ordinal() > this.ordinal();
    }

    public boolean canDowngradeTo(MembershipType newType) {
        return newType.ordinal() < this.ordinal();
    }

    @Override
    public String toString() {
        return String.format("%s {Max: %d books, %d days})", displayName, maxBooks, borrowDays);
    }
}
