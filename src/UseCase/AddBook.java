package UseCase;

import domain.dao.BookDao;
import domain.data.Book;
import infrastructure.factories.DaoFactory;

public class AddBook {
    public void execute (Book book) {
        BookDao bookDao = DaoFactory.getBookDao();
        bookDao.addBook(book);
    }
}
