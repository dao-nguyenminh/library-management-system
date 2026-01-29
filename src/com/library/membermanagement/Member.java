package com.library.membermanagement;

/*
+ Member ID (String) --> MEM-XXXXX --> Auto Generated
+ Personal info: name, email, phone
+ Join date (LocalDate) -  Instance
+ MembershipType (enum)
+ Status(enum)
+ Counters --> totalBooksBorrowed, currentBorrowedBooks
 */

/*
- Nên thiết kế Construtor như thế nào cho hợp lý?
- Option 1 --> Full Constructor (Đầy đủ tất cả các fields)
- Option 2 --> Essential fields only (Chỉ có một vài fields)
 */

import java.time.LocalDate;

/*
- memberId - Hệ thống này Id đang tự động tạo ra --> Không cần bên trong Constructor
- joinDate - Luôn là hôm nay khi tạo --> Không cần truyền vào
- status - Member mới luôn là ACTIVE --> Default Value
- totalBooksBorrowed, currentBorrowedBooks - Luôn là 0 lúc ban đầu --> default value = 0

=====> Chỉ cần những fields ngoài 4 th trên là user cần nhập
 */
public class Member {
    private String memberId;
    private String name;
    private String email;
    private String phone;
    private LocalDate joinDate;
    private MembershipType membershipType;
    private MemberStatus status;
    private int totalBooksBorrowed;
    private int currentBorrowedBooks;

    //Constructor
    public Member(String name, String email, String phone, MembershipType membershipType) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.membershipType = membershipType;
        this.joinDate = LocalDate.now();
        this.status = MemberStatus.ACTIVE;
        this.totalBooksBorrowed = 0;
        this.currentBorrowedBooks = 0;
    }

    //Business Logic Methods

    //Khi nào thì cần phải trở thành member --> Khi muốn mượn sách

    /**
     * Kiểm tra member có thẻ mượn thêm sách không?
     *
     * @return true nếu: ACTIVE + chưa vượt limit
     */
    public boolean canBorrow() {
        return status.isActive() && currentBorrowedBooks < membershipType.getMaxBooks();
    }

    /**
     * Tăng số sách đang mượn khi borrow
     */
    public void incrementBorrowCount() {
        if (!canBorrow()) {
            throw new IllegalStateException("Cannot increment: Member cannot borrow more books");
        }
        currentBorrowedBooks++;
        totalBooksBorrowed++;
    }

    /**
     * Giảm số sách đang mượn khi trả (return)
     */
    public void decrementBorrowCount() {
        if (currentBorrowedBooks <= 0) {
            throw new IllegalStateException("Cannot decrement: No book currently borrowed");
        }
        currentBorrowedBooks--;
    }

    /**
     * Suspend member (Không cho mượn sách mới)
     */
    public void suspend() {
        this.status = MemberStatus.SUSPENDED;
    }

    /**
     * Kích hoạt lại member
     */
    public void activate() {
        this.status = MemberStatus.ACTIVE;
    }

    /**
     * Upgrade/downgrade membership
     * Validation: Không downgrade nếu đang mượn quá limit mới
     */
    public void changeMembership(MembershipType newType) {
        if (newType.ordinal() < this.membershipType.ordinal()) {
            if (currentBorrowedBooks > newType.getMaxBooks()) {
                throw new IllegalStateException(
                        String.format("Cannot downgrade: Currently borrowing %d books, but %s only allows %d books",
                                currentBorrowedBooks,
                                newType.getDisplayName(),
                                newType.getMaxBooks())
                );
            }
        }
        this.membershipType = newType;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public MemberStatus getStatus() {
        return status;
    }

    public int getTotalBooksBorrowed() {
        return totalBooksBorrowed;
    }

    public int getCurrentBorrowedBooks() {
        return currentBorrowedBooks;
    }

    public int getMaxBooks() {
        return membershipType.getMaxBooks();
    }

    public int getBorrowDays() {
        return membershipType.getBorrowDays();
    }

    @Override
    public String toString() {
        return String.format("Member[ID=%s, Name=%s, Type=%s, Status=%s, Borrowed=%d/%d]",
                memberId,
                name,
                membershipType.getDisplayName(),
                status.getDisplayName(),
                currentBorrowedBooks,
                membershipType.getMaxBooks()
        );
    }
}
