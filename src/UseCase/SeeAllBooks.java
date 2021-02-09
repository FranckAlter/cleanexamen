package UseCase;

import domain.dao.BookDao;
import domain.data.Book;
import infrastructure.factories.DaoFactory;

import java.util.List;

public class SeeAllBooks {
    public List<Book> execute() {
        BookDao bookDao = DaoFactory.getBookDao();
        return bookDao.getAllBooks();
    }
}
