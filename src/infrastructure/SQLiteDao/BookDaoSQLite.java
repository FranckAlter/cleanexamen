package infrastructure.SQLiteDao;

import domain.dao.BookDao;
import domain.data.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoSQLite implements BookDao {
    private static BookDaoSQLite instance;

    private BookDaoSQLite() {
    }

    public static BookDaoSQLite getInstance() {
        if (instance == null) {
            instance = new BookDaoSQLite();
        }
        return instance;
    }

    @Override
    public List<Book> getAllBooks() {
        SQLiteJDBC connexion = SQLiteJDBC.getInstance();
        connexion.connect();
        List<Book> result = new ArrayList<>();
        try {
            ResultSet resultSet = connexion.statement.executeQuery("select * from book;");
            while (resultSet.next()) {
                result.add(new Book (resultSet.getString("title"), resultSet.getString("author")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur dans la requete");
        }
        connexion.close();
        return result;
    }

    @Override
    public List<Book> getAvailableBooks() {
        SQLiteJDBC connexion = SQLiteJDBC.getInstance();
        connexion.connect();
        List<Book> result = new ArrayList<>();
        try {
            ResultSet resultSet = connexion.statement.executeQuery("select * from book where available = 'True' or available = 'true';");
            while (resultSet.next()) {
                result.add(new Book (resultSet.getString("title"), resultSet.getString("author")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur dans la requete");
        }
        connexion.close();
        return result;
    }

    @Override
    public void addBook(Book book) {
        SQLiteJDBC connexion = SQLiteJDBC.getInstance();
        connexion.connect();
        String query = String.format("insert into book values ('%s', '%s', '%s');", book.getTitle(), book.getAuthor(), "True");
        try {
            connexion.statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connexion.close();

    }

    @Override
    public void borrowBook(Book book) {
        SQLiteJDBC connexion = SQLiteJDBC.getInstance();
        connexion.connect();
        String query = "update book SET available = 'False' where title =\'" + book.getTitle() + "\' and author =\'" + book.getAuthor() + "' ;";
        try {
            connexion.statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connexion.close();

    }

    @Override
    public void returnBook(Book book) {
        SQLiteJDBC connexion = SQLiteJDBC.getInstance();
        connexion.connect();
        String query = "update book SET available = 'True' where title =\'" + book.getTitle() + "\' and author =\'" + book.getAuthor() + "' ;";
        try {
            connexion.statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connexion.close();

    }

    @Override
    public int getId(Book book) {
        SQLiteJDBC connexion = SQLiteJDBC.getInstance();
        connexion.connect();
        int result = 0;
        try {
            ResultSet resultSet = connexion.statement.executeQuery(String.format("select rowid from book where title = '%s' and author = '%s';", book.getTitle(), book.getAuthor()));
            while (resultSet.next()) {
                result = resultSet.getInt("rowid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur dans la requete");
        }
        return result;
    }

    @Override
    public Book getBookById(int id) {
        SQLiteJDBC connexion = SQLiteJDBC.getInstance();
        connexion.connect();
        Book result = null;
        try {
            ResultSet resultSet = connexion.statement.executeQuery(String.format("select * from book where rowid = %d;", id));
            while (resultSet.next()) {
                result = new Book (resultSet.getString("title"), resultSet.getString("author"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur dans la requete");
        }
        return result;
    }

}
