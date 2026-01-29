package com.library.test;

import com.library.membermanagement.Member;
import com.library.membermanagement.MemberManager;
import com.library.membermanagement.MemberManagerImpl;
import com.library.membermanagement.MembershipType;

public class TestMemberManagement {

    public static void main(String[] args) {
        MemberManager memberManager = new MemberManagerImpl();

        //Test 1: Add Members
        System.out.println("=== Test 1: Add members ===");
        String id1 = memberManager.addMember("Nguyen Van A", "vana@gmail.com",
                "0123456789", MembershipType.STANDARD);
        String id2 = memberManager.addMember("Nguyen Van B", "vanb@gmail.com",
                "0120230201", MembershipType.STANDARD);
        String id3 = memberManager.addMember("Nguyen Minh Dao", "daongminh@gmail.com",
                "0942236357", MembershipType.STANDARD);

        System.out.println("Created: " + id1);
        System.out.println("Created: " + id2);
        System.out.println("Created: " + id3);

        //Test 2: Find by ID
        System.out.println("\n=== Test 2: Find By ID ===");
        Member member1 = memberManager.findById(id1);
        System.out.println(member1);

        //Test 3: Search by name
        System.out.println("\n=== Tes 3: Search By Name ===");
        memberManager.searchByName("Van").forEach(System.out::println);

        //Test 4: Update member
        System.out.println("\n=== Test 4: Update Member ===");
        memberManager.updateMember(id1, "Nguyen Van A Updated", "0986351681");
        System.out.println(memberManager.findById(id1));

        //Test 5: Suspend member
        System.out.println("\n=== Test 5: Suspend Member ===");
        memberManager.suspendMember(id1);
        System.out.println("Can borrow: " + memberManager.findById(id1).canBorrow());

        //Test 6: Activate member
        System.out.println("\n=== Test 6: Activate Member ===");
        memberManager.activateMember(id1);
        System.out.println("Can borrow: " + memberManager.findById(id1).canBorrow());

        //Test 7: Upgrade membership
        System.out.println("\n=== Test 7: Upgrade Membership ===");
        Member standardMember = memberManager.findById(id1);
        System.out.println("Before: " + standardMember.getMembershipType());
        memberManager.changeMembership(id1, MembershipType.PREMIUM);
        System.out.println("After: " + standardMember.getMembershipType());

        //Test 8: Try to add duplicate email
        System.out.println("\n=== Test 8: Duplicate Email ===");
        try {
            memberManager.addMember("Duplicate", "vana@gmail.com",
                    "0123456781", MembershipType.STANDARD);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        //Test 9: Invalid Phone:

        //Test 10: Invalid Email

        //Test 11: Get all members sorted

        //Test 12: Filter by type

        //Test 13: Static fields (Total Members, ...)
    }
}
