package domain.dao;

import domain.data.Book;
import domain.data.Borrow;
import domain.data.person.Member;

import java.util.List;

public interface BorrowDao {
    public void addBorrow (Borrow borrow);
    public void returnBorrow (Borrow borrow);
    public int getNumberBorrowsForMember (Member member);
    public List<Book> getBorrowBookForMember (Member member);

}
