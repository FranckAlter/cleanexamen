package domain.dao;

import domain.data.Book;

import java.util.List;

public interface BookDao {
    public List<Book> getAllBooks ();
    public List<Book> getAvailableBooks ();
    public void addBook (Book book);
    public void borrowBook (Book book);
    public void returnBook (Book book);
    public int getId (Book book);
    public Book getBookById (int id);

}
