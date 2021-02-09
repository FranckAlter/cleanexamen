package UseCase;

import domain.data.Borrow;
import infrastructure.factories.DaoFactory;

public class ReturnBook {
    public void execute (Borrow borrow) {
        DaoFactory.getBorrowDao().returnBorrow(borrow);
        DaoFactory.getBookDao().returnBook(borrow.getBook());
    }
}
