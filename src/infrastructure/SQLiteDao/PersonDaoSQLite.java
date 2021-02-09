package infrastructure.SQLiteDao;

import domain.dao.PersonDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PersonDaoSQLite implements PersonDao {

    private static PersonDaoSQLite instance;

    private PersonDaoSQLite() {
    }

    public static PersonDaoSQLite getInstance() {
        if (instance == null) {
            instance = new PersonDaoSQLite();
        }
        return instance;
    }

    @Override
    public String getTypePerson(int id) {
        SQLiteJDBC connexion = SQLiteJDBC.getInstance();
        connexion.connect();
        String s = null;
        try {
            ResultSet resultSet = connexion.statement.executeQuery(String.format("select role from person where rowid = %s;", id));
            while (resultSet.next()) {
                s = resultSet.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur dans la requete");
        }
        connexion.close();
        return s;
    }

    @Override
    public List<Integer> getAllId() {
        SQLiteJDBC connexion = SQLiteJDBC.getInstance();
        connexion.connect();
        List <Integer> result = new ArrayList<>();
        try {
            ResultSet resultSet = connexion.statement.executeQuery("select rowid from person;");
            while (resultSet.next()) {
                result.add(resultSet.getInt("rowid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur dans la requete");
        }
        connexion.close();
        return result;
    }
}
