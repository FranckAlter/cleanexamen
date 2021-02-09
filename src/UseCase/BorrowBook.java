package UseCase;

import domain.data.Borrow;
import infrastructure.factories.DaoFactory;

public class BorrowBook {
    public void execute (Borrow borrow) {
        int nbBorrow = DaoFactory.getBorrowDao().getNumberBorrowsForMember(borrow.getMember());
        if (nbBorrow > 2) {
            System.out.println("impossible d'emprunter plus de 3 livres, merci d'en rendre avant d'en emprunter de nouveau");
        }
        else {
            DaoFactory.getBorrowDao().addBorrow(borrow);
            DaoFactory.getBookDao().borrowBook(borrow.getBook());
        }
    }
}
