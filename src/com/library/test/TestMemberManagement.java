package com.library.test;

import com.library.membermanagement.Member;
import com.library.membermanagement.MembershipType;

import java.util.logging.Logger;

public class TestMemberManagement {
    private static final Logger logger = Logger.getLogger(TestBookManagement.class.getName());

    public static void main(String[] args) {
        Member member = new Member(
                "Test",
                "test@gmail.com",
                "0123456789",
                MembershipType.STANDARD
        );

        try {
            //1. Mượn 3 cuốn
            member.incrementBorrowCount();
            logger.info("Successfully");

            member.incrementBorrowCount();
            logger.info("Successfully");

            member.incrementBorrowCount();
            logger.info("Successfully");

            //2. Cố tình mượn cuốn thứ 4 đối với Membershipe type = STANDARD
            member.incrementBorrowCount();
            logger.info("Successfully");
        } catch (IllegalStateException ex) {
            logger.severe(ex.getMessage());
        }
    }
}
