package com.example.springbootproof.dao;

import com.example.springbootproof.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDAO {

    private static final List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(Person person) {
        DB.add(new Person(person.getId(),person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return null;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if (personMaybe.isEmpty()){
            return 0;
        }
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID idPerson, Person updatedPerson) {
        return selectPersonById(idPerson).map(person -> {
            int indexOfPersonToDelete = DB.indexOf(person);
            if (indexOfPersonToDelete >= 0){
                DB.set(indexOfPersonToDelete, new Person(idPerson, updatedPerson.getName()));
                return 1;
            }
            return 0;
        }).orElse(8);
    }
}
