package infrastructure.factories;


import domain.dao.BookDao;
import domain.dao.BorrowDao;
import domain.dao.PersonDao;
import infrastructure.SQLiteDao.BookDaoSQLite;
import infrastructure.SQLiteDao.BorrowDaoSQLite;
import infrastructure.SQLiteDao.PersonDaoSQLite;

public class DaoFactory {
    public static BookDao getBookDao(){
        return BookDaoSQLite.getInstance();
    }
    public static PersonDao getPersonDao(){
        return PersonDaoSQLite.getInstance();
    }
    public static BorrowDao getBorrowDao(){
        return BorrowDaoSQLite.getInstance();
    }



}
