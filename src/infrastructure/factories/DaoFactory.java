package infrastructure.factories;

public class DaoFactory {

    public static PersonDao getPersonDao(){
        return PersonDaoMemoryList.getInstance();
    }

    public static BookDao getBookDao(){
        return BookDaoMemoryList.getInstance();
    }

    public static BorrowDao getBorrowDao(){
        return BorrowDaoMemoryList.getInstance();
    }


}
