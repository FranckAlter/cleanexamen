package infrastructure.listDao;

import domain.data.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDaoMemoryList {
    private List<Book> books = new ArrayList<Book>();
    private static BookDaoMemoryList instance;

    private BookDaoMemoryList() {
    }

    public static BookDaoMemoryList getInstance() {
        if (instance == null) {
            instance = new BookDaoMemoryList();
        }
        return instance;
    }
}
