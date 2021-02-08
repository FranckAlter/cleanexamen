package domain.dao;

import domain.data.person.Borrow;
import domain.data.person.Member;

import java.util.Set;

public interface BorrowDao {
    public void addBorrow (Borrow borrow);
    public void returnBorrow (Borrow borrow);
    public int getNumberBorrowsForMember (Member member);
    public Set<Borrow> getBorrowsForMember (Member member);

}
