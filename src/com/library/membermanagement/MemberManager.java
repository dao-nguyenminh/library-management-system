package com.library.membermanagement;

import java.util.List;

/*
addMember(member) -> String (memberId)
updateMember(memberId, updateInfo) --> boolean
deleteMember(memberId) --> boolean
findById(memberId) --> Member
searchByName(keyword) --> List<Member>
searchByEmail(email) --> Member
getAllMembes() --> List<Member>

//utils methods
generateMemberId() --> String (MEM-XXXXX)
isEmailExists(email) --> boolean
*/
public interface MemberManager {
    String addMember(String name, String email, String phone, MembershipType membershipType);

    String addMember(Member member);

    Member findById(String memberId);

    Member findByEmail(String email);

    List<Member> searchByName(String keyword);

    List<Member> getAllMembers();

    List<Member> getMemberByStatus(MemberStatus status);

    List<Member> getMemberByType(MembershipType type);

    boolean updateMember(String memberId, String name, String phone);

    boolean deleteMember(String memberId);

    boolean suspendMember(String memberId);

    boolean activateMember(String memberId);

    boolean changeMembership(String memberId, MembershipType newType);

    int getTotalMembers();

    int getActiveMember();

    List<Member> sortByName();

    List<Member> sortByJoinDate();

    List<Member> sortByTotalBorrowed();
}
