package infrastructure.SQLiteDao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteJDBC {
    final private String DBPath = "resources/memory.db";
    private Connection connection = null;
    public Statement statement = null;
    private static SQLiteJDBC instance;

    private SQLiteJDBC() {
    }

    public static SQLiteJDBC getInstance() {
        if (instance == null) {
            instance = new SQLiteJDBC();
        }
        return instance;
    }

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
            statement = connection.createStatement();
        } catch (ClassNotFoundException notFoundException) {
            notFoundException.printStackTrace();
            System.out.println("Erreur de connecxion");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Erreur de connecxion");
        }
    }

    public void close() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
