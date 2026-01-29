package com.library.membermanagement;

import com.library.utils.InputValidator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MemberManagerImpl implements MemberManager {
    private final HashMap<String, Member> members; //memberId -> Member
    private int memberCounter;

    public MemberManagerImpl() {
        this.members = new HashMap<>();
        this.memberCounter = 0;
    }

    //Generate Member ID
    private String generateMemberId() {
        memberCounter++;
        return String.format("MEM-%05d", memberCounter);
    }

    @Override
    public String addMember(String name, String email, String phone, MembershipType membershipType) {
        //Validate Input
        if (!InputValidator.isNonEmpty(name)) {
            throw new IllegalArgumentException("Tên không được rỗng");
        }

        if (!InputValidator.isValidEmail(email)) {
            throw new IllegalArgumentException("Email không hợp lệ: " + email);
        }

        if (!InputValidator.isValidPhone(phone)) {
            throw new IllegalArgumentException("Số điện thoại phải có 10 chữ số và bất đầu bằng 0: " + phone);
        }

        if (isEmailExists(email)) {
            throw new IllegalArgumentException("Email đã tồn tại: " + email);
        }

        //Create Member
        Member member = new Member(name, email, phone, membershipType);
        String memberId = generateMemberId();
        member.setMemberId(memberId);

        //Save
        members.put(memberId, member);

        return memberId;
    }

    private boolean isEmailExists(String email) {
        return findByEmail(email) != null;
    }

    @Override
    public String addMember(Member member) {
        return addMember(member.getName(), member.getEmail(), member.getPhone(), member.getMembershipType());
    }

    @Override
    public Member findById(String memberId) {
        if (!InputValidator.isValidMemberId(memberId)) {
            throw new IllegalArgumentException("Member ID không hợp lệ: " + memberId);
        }
        return members.get(memberId);
    }

    @Override
    public Member findByEmail(String email) {
        return members.values().stream()
                .filter(member -> member.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Member> searchByName(String keyword) {
        if (keyword.isBlank()) {
            return new ArrayList<>();
        }

        String lowerKeyword = keyword.toLowerCase();
        return members.values().stream()
                .filter(member -> member.getName().toLowerCase().contains(lowerKeyword))
                .toList();
    }

    @Override
    public List<Member> getAllMembers() {
        return new ArrayList<>(members.values());
    }

    @Override
    public List<Member> getMemberByStatus(MemberStatus status) {
        return members.values().stream()
                .filter(member -> member.getStatus() == status)
                .toList();
    }

    @Override
    public List<Member> getMemberByType(MembershipType type) {
        return members.values().stream()
                .filter(member -> member.getMembershipType() == type)
                .toList();
    }

    @Override
    public boolean updateMember(String memberId, String name, String phone) {
        Member member = findById(memberId);

        if (member == null) {
            return false;
        }

        if (!InputValidator.isNonEmpty(name)) {
            throw new IllegalArgumentException("Tên không được rỗng");
        }

        if (!InputValidator.isValidPhone(phone)) {
            throw new IllegalArgumentException("Số điện thoại không hợp lệ");
        }

        member.setName(name);
        member.setPhone(phone);

        return true;
    }

    @Override
    public boolean deleteMember(String memberId) {
        Member member = findById(memberId);

        if (member == null) {
            return false;
        }

        if (member.getCurrentBorrowedBooks() > 0) {
            throw new IllegalStateException(
                    String.format("Không thể xoá member %s: Đang mượn %d sách", memberId, member.getCurrentBorrowedBooks())
            );
        }

        members.remove(memberId);
        return true;
    }

    @Override
    public boolean suspendMember(String memberId) {
        Member member = findById(memberId);

        if (member == null) {
            return false;
        }

        member.suspend();
        return true;
    }

    @Override
    public boolean activateMember(String memberId) {
        Member member = findById(memberId);

        if (member == null) {
            return false;
        }

        member.activate();
        return true;
    }

    @Override
    public boolean changeMembership(String memberId, MembershipType newType) throws IllegalStateException {
        Member member = findById(memberId);

        if (member == null) {
            return false;
        }

        member.changeMembership(newType);
        return true;
    }

    @Override
    public int getTotalMembers() {
        return members.size();
    }

    @Override
    public int getActiveMember() {
        return (int) members.values().stream()
                .filter(member -> member.getStatus() == MemberStatus.ACTIVE)
                .count();
    }

    @Override
    public List<Member> sortByName() {
        return members.values().stream()
                .sorted(Comparator.comparing(Member::getName))
                .toList();
    }

    @Override
    public List<Member> sortByJoinDate() {
        return members.values().stream()
                .sorted(Comparator.comparing(Member::getJoinDate))
                .toList();
    }

    @Override
    public List<Member> sortByTotalBorrowed() {
        return members.values().stream()
                .sorted(Comparator.comparing(Member::getTotalBooksBorrowed))
                .toList();
    }
}
