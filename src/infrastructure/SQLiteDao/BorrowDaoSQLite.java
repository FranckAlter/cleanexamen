package infrastructure.SQLiteDao;

import domain.dao.BorrowDao;
import domain.data.Book;
import domain.data.Borrow;
import domain.data.person.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowDaoSQLite implements BorrowDao {
    private static BorrowDaoSQLite instance;

    private BorrowDaoSQLite() {
    }

    public static BorrowDaoSQLite getInstance() {
        if (instance == null) {
            instance = new BorrowDaoSQLite();
        }
        return instance;
    }

    @Override
    public void addBorrow(Borrow borrow) {
        SQLiteJDBC connexion = SQLiteJDBC.getInstance();
        connexion.connect();
        int id_book = BookDaoSQLite.getInstance().getId(borrow.getBook());
        String query = String.format("insert into borrow values (%d, %d, %s, 'False')", id_book, borrow.getMember().getId(), borrow.getLoanDate().toString());
        try {
            connexion.statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connexion.close();
    }

    @Override
    public void returnBorrow(Borrow borrow) {
        SQLiteJDBC connexion = SQLiteJDBC.getInstance();
        connexion.connect();
        int id_book = BookDaoSQLite.getInstance().getId(borrow.getBook());
        String query = String.format("update borrow SET returned = 'True' where book_id = %d and member_id = %d", id_book, borrow.getMember().getId());
        try {
            connexion.statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connexion.close();
    }

    @Override
    public int getNumberBorrowsForMember(Member member) {
        SQLiteJDBC connexion = SQLiteJDBC.getInstance();
        connexion.connect();
        int result = 0;
        try {
            ResultSet resultSet = connexion.statement.executeQuery("select * from borrow where returned = 'False' and member_id=" + member.getId() + ";");
            while (resultSet.next()) {
                result ++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur dans la requete");
        }
        connexion.close();
        return result;
    }

    @Override
    public List<Book> getBorrowBookForMember(Member member) {
        SQLiteJDBC connexion = SQLiteJDBC.getInstance();
        connexion.connect();
        List<Book> result = new ArrayList<>();
        try {
            ResultSet resultSet = connexion.statement.executeQuery("select * from borrow where returned = 'False' and member_id=" + member.getId() + ";");
            while (resultSet.next()) {
                Book book = BookDaoSQLite.getInstance().getBookById(resultSet.getInt("book_id"));
                result.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur dans la requete");
        }
        connexion.close();
        return result;
    }
}
