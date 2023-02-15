package com.example.springbootproof.dao;

import com.example.springbootproof.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDAO{
    @Override
    public int insertPerson(Person person) {
        return 0;
    }

    @Override
    public List<Person> selectAllPeople() {
        return List.of(new Person(UUID.randomUUID(), ""));
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return Optional.empty();
    }

    @Override
    public int deletePersonById(UUID id) {
        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return 0;
    }
}