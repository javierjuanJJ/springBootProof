package com.example.springbootproof.dao;

import com.example.springbootproof.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDAO {
    int insertPerson(Person person);
    default int addPerson(Person person){
        UUID idPerson = UUID.randomUUID();
        person.setId(idPerson);
        return insertPerson(person);
    }
    List<Person> selectAllPeople();
    Optional<Person> selectPersonById(UUID id);
    int deletePersonById(UUID id);
    int updatePersonById(UUID id, Person person);
}
