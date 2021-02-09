package domain.dao;

import domain.data.person.Person;

import java.util.List;

public interface PersonDao {
    String getTypePerson(int id);
    List<Integer> getAllId ();
}
