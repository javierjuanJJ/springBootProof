package com.example.springbootproof.service;

import com.example.springbootproof.dao.PersonDAO;
import com.example.springbootproof.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class PersonService {
    private final PersonDAO personDAO;

    @Autowired
    public PersonService(@Qualifier("postgres") PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    public int addPerson(Person person){
        return personDAO.insertPerson(person);
    }

    public List<Person> selectAllPeople(){
        return personDAO.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID id){
        return personDAO.selectPersonById(id);
    }

    public int deletePerson(UUID id){
        return personDAO.deletePersonById(id);
    }

    public int updatePerson(UUID id, Person newPerson){
        return personDAO.updatePersonById(id, newPerson);
    }
}
